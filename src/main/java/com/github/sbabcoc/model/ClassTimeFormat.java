package com.github.sbabcoc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClassTimeFormat {

	private static final String DATE_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_TEMPLATE, Locale.ENGLISH);
	
	private ClassTimeFormat() {
        throw new AssertionError("ClassTimeFormat is a static utility class that cannot be instantiated");
	}
	
	public static Date parse(String timeStr) {
    	try {
			return DATE_FORMAT.parse(timeStr);
		} catch (ParseException e) {
			throw new IllegalStateException(
					String.format("Class time '%s' failed to parse with template: %s", timeStr, DATE_TEMPLATE), e);
		}
	}
	
}
