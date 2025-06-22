package com.dharshan.Warehouse_Management.Transportation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;



@Component
public class TrackingNumberGeneration {

	
		public String generate() {
			//RandomStringUtils from pom.xml la common.lang3 dependency la irunthu varuthu
			String letters= RandomStringUtils.randomAlphabetic(3).toUpperCase();
			String dates= LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
			String numbers=RandomStringUtils.randomNumeric(4);
			return letters+dates+numbers;
		}
}
