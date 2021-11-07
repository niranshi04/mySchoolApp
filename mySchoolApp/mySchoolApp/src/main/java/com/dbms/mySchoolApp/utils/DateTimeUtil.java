package com.dbms.mySchoolApp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtil {

    /**
     * Get current dateTime in specified format e.g: yyyy-MM-dd HH:mm:ss
     *
     * @param format
     * @return String dateTime
     */
    public String getCurrentDateTime(String format) {
        SimpleDateFormat localDateTimeFormat = new SimpleDateFormat(format);
        localDateTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String dateTime = localDateTimeFormat.format(new Date());
        return dateTime;
    }
}
