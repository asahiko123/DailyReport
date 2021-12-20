package com.example.demo.app.dailyreport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.service.DailyReportService;
import com.example.demo.app.stuff.StuffForm;
import com.example.demo.app.work.WorkForm;

@Controller
@RequestMapping("/main")
public class DailyReportController {
	
	private final DailyReportService dailyReportService;
	private int id;
	private String stuff;
	private String work;
	private String progress;
	private String date;
	private String start;
	private String end;
	private String detail;
	
	
	public DailyReportController(DailyReportService dailyReportService) {
		this.dailyReportService = dailyReportService;
		
	}
	
	@GetMapping
	public String top(Model model) {
		
		model.addAttribute("title","日報アプリ");

		return"top";
	}
	
	
	@GetMapping("/report")
	public String DailyReport(DailyReportForm dailyReportForm,
			Model model) {

		dailyReportForm.setNewReport(true);
		List<DailyReport> list = dailyReportService.findAll();
		List<Stuff> stuff = dailyReportService.findStuff();
		List<Work> work = dailyReportService.findWork();
		
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		model.addAttribute("title","日報入力");
		
	
		
		return "DailyReportForm";
	}
	
	
	@GetMapping("/report/{id}")
	public String showUpdate(
			@PathVariable int id,
			DailyReportForm dailyReportForm,
			Model model) {
		
		Optional<DailyReport>dailyReportOpt = dailyReportService.getDailyReport(id);
		Optional<DailyReportForm> dailyReportFormOpt = dailyReportOpt.map(t -> makeDailyReportForm(t));
		
		if(dailyReportOpt.isPresent()) {
			
			dailyReportForm  = dailyReportFormOpt.get();
		}
		
		
		model.addAttribute("title","日報更新フォーム");
		model.addAttribute("DailyReportForm",dailyReportForm);
		List<DailyReport> list = dailyReportService.findAll();
		List<Stuff> stuff = dailyReportService.findStuff();
		List<Work> work = dailyReportService.findWork();
		
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		model.addAttribute("dailyReportId",id);
     	
		
		return "DailyReportForm";
		
	}
	
	@PostMapping("/report/insert")
	public String insert(
			@Valid@ModelAttribute DailyReportForm dailyReportForm,
			BindingResult result,
			StuffForm stuffForm,
			WorkForm workForm,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		
		
		DailyReport dailyReport  = makeDailyReport(dailyReportForm,0);

		if(!result.hasErrors()) {
			dailyReportService.insert(dailyReport);
			redirectAttributes.addFlashAttribute("dailyReportForm",dailyReportForm);
			return "redirect:/main/report";
		}else {
			dailyReportForm.setNewReport(true);
			model.addAttribute("DailyReportForm",dailyReportForm);
			
			for(ObjectError error:result.getAllErrors()) {
				
				ArrayList<String> dayCheck = new ArrayList<String>();
				
				String errorcheck = error.getDefaultMessage();
				dayCheck.add(errorcheck);
				System.out.println(dayCheck);
				model.addAttribute("dayCheck",dayCheck);
			}
		
			List<DailyReport> list = dailyReportService.findAll();
			List<Stuff> stuff = dailyReportService.findStuff();
			List<Work> work = dailyReportService.findWork();
			
			model.addAttribute("stuff",stuff);
			model.addAttribute("work",work);
			model.addAttribute("list",list);
			model.addAttribute("title","日報入力");
			
			return"DailyReportForm";
		}
		
	}
	
	@PostMapping("/report/update")
	public String update(
			@Valid@ModelAttribute DailyReportForm dailyReportForm,
			BindingResult result,
			@RequestParam("dailyReportId")int dailyReportId,
			@RequestParam("stuffId")int stuffId,
			StuffForm stuffForm,
			WorkForm workForm,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		
		if(!result.hasErrors()) {
			DailyReport dailyReport = makeDailyReport(dailyReportForm,dailyReportId);
		

			dailyReportService.update(dailyReport);
			
			redirectAttributes.addFlashAttribute("complete","変更しました");
			redirectAttributes.addFlashAttribute("dailyReportForm",dailyReportForm);
			return "redirect:/main/report/"+dailyReportId;
			
		}else {
			model.addAttribute("dailyReportForm",dailyReportForm);
			model.addAttribute("title","日報更新フォーム");
		
			
			return"DailyReportForm";
		}
		
	}
	
	@PostMapping("/report/delete")
	public String delete(
			Model model,
			DailyReport dailyReport,
			@RequestParam("dailyReportId")int id
			) {
		
		dailyReportService.deleteById(id);
		return"redirect:/main/report";
		
	}
	
	
	

	
	//エクセル出力を行う

	@RequestMapping("/report/output")
	public String download(RedirectAttributes redirectAttributes,
			               DailyReportForm dailyReportForm,
			               DailyReport dailyRport,
			               Model model) throws IOException {
	
  
	  
    List<DailyReport> list = dailyReportService.findAll();
    int i = 1;
    
	//テンプレートファイルの参照、読み込み
    //Pathはローカルで指定
    
    Path tempPath = Paths.get("src\\main\\resources\\static\\excel\\template_dailyReport.xlsx");
    InputStream inst = Files.newInputStream(tempPath);
    Workbook workbook = new XSSFWorkbook(inst);
    FileOutputStream out = null;
    
    //シート、セル、行の作成
    
    Sheet sheet = workbook.getSheetAt(0);
    Cell[][] cell;
    cell = new Cell[7][8];
    Row[] row;
    row = new Row[7];
    
    //スタイル指定
    
    CellStyle style = workbook.createCellStyle();
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    

    

	//セルに値を設定
    
	for(DailyReport objct:list) {
		
		id    = objct.getId();
		stuff = objct.getStuff().getRegisteredId();
		date = objct.getCreated();
		start = objct.getStartTime();
		end   = objct.getEndTime();
		detail = objct.getDetail();
		progress = objct.getDailyReportType().getProgress();
		work = objct.getWork().getWorkDivId();
		
		
		row[i] = sheet.createRow(i);

        cell[i][0] = row[i].createCell(0);
        cell[i][1] = row[i].createCell(1);
        cell[i][2] = row[i].createCell(2);
        cell[i][3] = row[i].createCell(3);
        cell[i][4] = row[i].createCell(4);
        cell[i][5] = row[i].createCell(5);
        cell[i][6] = row[i].createCell(6);
        cell[i][7] = row[i].createCell(7);
       
        cell[i][0].setCellValue(id);
        cell[i][0].setCellStyle(style);
        cell[i][1].setCellValue(stuff);
        cell[i][1].setCellStyle(style);
        cell[i][2].setCellValue(progress);
        cell[i][2].setCellStyle(style);
        cell[i][3].setCellValue(work);
        cell[i][3].setCellStyle(style);
        cell[i][4].setCellValue(date);
        cell[i][4].setCellStyle(style);
        cell[i][5].setCellValue(start);
        cell[i][5].setCellStyle(style);
        cell[i][6].setCellValue(end);
        cell[i][6].setCellStyle(style);
        cell[i][7].setCellValue(detail);
        cell[i][7].setCellStyle(style);
        
	    i++;
	}
 
		


    out = new FileOutputStream("C:\\Users\\hullh\\result");

    workbook.write(out);
    workbook.close();
    redirectAttributes.addFlashAttribute("output","エクセル出力しました");
	
    return "redirect:/main/report";
    
	}
	
	
	private DailyReport makeDailyReport(DailyReportForm dailyReportForm,int dailyReportId) {
	
		DailyReport dailyReport = new DailyReport();
		
		
		if(dailyReportId != 0) {
			dailyReport.setId(dailyReportId);
			
		}
		
		dailyReport.setStartTime(dailyReportForm.getStartTime());
		dailyReport.setEndTime(dailyReportForm.getEndTime());
		dailyReport.setDiff((String)dailyReportForm.getDiff());
       	dailyReport.setCreated(dailyReportForm.getCreated());	
		dailyReport.setDetail(dailyReportForm.getDetail());
		dailyReport.setName(dailyReportForm.getName());
		dailyReport.setTypeId(dailyReportForm.getTypeId());
		dailyReport.setStuffId(dailyReportForm.getStuffId());
		dailyReport.setWorkId(dailyReportForm.getWorkId());
		dailyReport.setRegisteredId(dailyReportForm.getRegisteredId());
		dailyReport.setStartDate(dailyReportForm.getStartDate());
		dailyReport.setEndDate(dailyReportForm.getEndDate());
	
		
		return dailyReport;
	}
	
	private DailyReportForm makeDailyReportForm(DailyReport dailyReport) {
		
		DailyReportForm dailyReportForm = new DailyReportForm();
		
		dailyReportForm.setId(dailyReport.getId());
		dailyReportForm.setStartTime(dailyReport.getStartTime());
		dailyReportForm.setEndTime(dailyReport.getEndTime());
		dailyReportForm.setDiff(dailyReport.getDiff());
		dailyReportForm.setCreated(dailyReport.getCreated());
	    dailyReportForm.setName(dailyReport.getName());
		dailyReportForm.setDetail(dailyReport.getDetail());
		dailyReportForm.setTypeId(dailyReport.getTypeId());
		dailyReportForm.setStuffId(dailyReport.getStuffId());
		dailyReportForm.setWorkId(dailyReport.getWorkId());
		dailyReportForm.setStartDate(dailyReport.getStartDate());
		dailyReportForm.setEndDate(dailyReport.getEndDate());
		
		
		dailyReportForm.setNewReport(false);
		
		return dailyReportForm;
	}

}
