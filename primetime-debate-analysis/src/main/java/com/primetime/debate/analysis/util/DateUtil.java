package com.primetime.debate.analysis.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DateUtil {
	
	//2020-09-29T17:55:44.000Z
public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    private static Logger logger = LogManager.getLogger(DateUtil.class);
    
    public static Date convertStringToDate(String str) {
        Date dafaultDate = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        Date d = dafaultDate;
        try {
            d = sdf.parse(str);
        } catch(Exception e) {
        	logger.error("Error encountered in coverting String to Date. Date: " + str + "Format: " + DEFAULT_DATE_FORMAT, e);
        }
        return d;
    }
}
