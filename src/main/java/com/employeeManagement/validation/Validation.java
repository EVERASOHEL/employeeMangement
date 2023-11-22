package com.employeeManagement.validation;

import com.employeeManagement.constant.CommonConstant;

import java.awt.datatransfer.StringSelection;
import java.util.Objects;

public class Validation {

	public static void throwsRequiredException(String key) throws AppException {
		throw new AppException(key);
	}
	
	public static void IsnullOrEmpty(String key, String value) throws AppException {
		if (value==null || value.isEmpty()) {
			throwsRequiredException(key);
		}
	}

	public static Boolean IsnullOrEmptyValue(String key, String value){
		if (value==null || value.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void IsnullOrEmptyOrisNotDigit(String key, String value) throws AppException {
		
		IsnullOrEmpty(key,value);

		if(value.equals("0.0")){
			throwsRequiredException(key);
		}

		boolean isDecimal = true;
		int count = 0;

		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);

			if (!Character.isDigit(ch)) {
				if (ch == '.') {
					count++;
					if (count > 1) {
						isDecimal = false;
						break;
					}
				} else {
					isDecimal = false;
					break;
				}
			}
		}
		
		if(!isDecimal || count > 1) {
			throwsRequiredException(key);
		}
	}

	public static Boolean IsnullOrEmptyOrisNotDigitValue(String key, String value){

		IsnullOrEmptyValue(key,value);

		if(value.equals("0.0")){
			return false;
		}

		boolean isDecimal = true;
		int count = 0;

		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);

			if (!Character.isDigit(ch)) {
				if (ch == '.') {
					count++;
					if (count > 1) {
						isDecimal = false;
						break;
					}
				} else {
					isDecimal = false;
					break;
				}
			}
		}

		if(!isDecimal || count > 1) {
			return false;
		}
		return true;
	}

	public static void isNotDigit(String key, String value) throws AppException {
		
		boolean isDecimal = true;
		int count = 0;

		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);

			if (!Character.isDigit(ch)) {
				if (ch == '.') {
					count++;
					if (count > 1) {
						isDecimal = false;
						break;
					}
				} else {
					isDecimal = false;
					break;
				}
			}
		}

		if(!isDecimal || count > 1) {
			throwsRequiredException(key);
		}
	}
	
//	public static void isStringDigits(String key, String value) throws AppException {
//		IsnullOrEmpty(key, value);
//		for (int i = 0; i < value.length(); i++) {
//			if (!Character.isDigit(value.charAt(i))) {
//				throwsRequiredException(key);
//			}
//		}
//	}
}
