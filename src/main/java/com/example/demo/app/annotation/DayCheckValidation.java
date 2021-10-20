package com.example.demo.app.annotation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.app.dailyreport.DailyReportForm;

public class DayCheckValidation implements ConstraintValidator<DayCheck,DailyReportForm>{
	
	@Override
	public boolean isValid(DailyReportForm value,ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		return isValidCompare(value,context);
//				&isValidEqual(value,context);
	}
	
	public boolean isValidCompare(DailyReportForm value,ConstraintValidatorContext context) {
		
		if(value.getStartTime()==null || value.getEndTime()==null) {
			return false;
		}
		context.buildConstraintViolationWithTemplate("終了日時は開始日時よりもあとの時刻を入力してください").addConstraintViolation();
		return(LocalTime.parse(value.getStartTime())).isBefore(LocalTime.parse(value.getEndTime()))?true:false;
		
	}
}
//	public boolean isValidEqual(DailyReportForm value,ConstraintValidatorContext context) {
//		if(value.getCreated()==null || value.getStartTime()==null) {
//			return false;
//		}
//		context.buildConstraintViolationWithTemplate("作業日時と開始日時が違います").addConstraintViolation();
//		LocalDateTime toLocaldate =LocalDateTime.parse(value.getStartTime());
//		LocalDate date =LocalDate.of(toLocaldate.getYear(), toLocaldate.getMonth(), toLocaldate.getDayOfMonth());
//		System.out.println("created"+value.getCreated());
//		System.out.println(LocalDate.parse(value.getCreated()).isEqual(date) ?true:false);
//		return(LocalDate.parse(value.getCreated())).isEqual(date) ?true:false;
//	}
//}
