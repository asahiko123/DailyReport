package com.example.demo.app.annotation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.app.dailyreport.DailyReportForm;

public class DayCheckValidation implements ConstraintValidator<DayCheck,DailyReportForm>{
	
	@Override
	public boolean isValid(DailyReportForm value,ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		return isValidCompare(value,context);
	}
	
	public boolean isValidCompare(DailyReportForm value,ConstraintValidatorContext context) {
		
		if(value.getStartTime()==null || value.getEndTime()==null) {
			return false;
		}
		context.buildConstraintViolationWithTemplate("終了日時は開始日時よりもあとの時刻を入力してください").addConstraintViolation();
		return(LocalDateTime.parse(value.getStartTime())).isBefore(LocalDateTime.parse(value.getEndTime()))?true:false;
		
	}
	
//	public boolean isValidEqual(DailyReportForm value,ConstraintValidatorContext context) {
//		if(value.getCreated()==null || value.getStartTime()==null) {
//			return false;
//		}
//		context.buildConstraintViolationWithTemplate("作業日時と開始日時が違います").addConstraintViolation();
//		return(LocalDate.parse(value.getCreated())).equals(LocalDateTime.parse(value.getStartTime()))?true:false;
//	}
}
