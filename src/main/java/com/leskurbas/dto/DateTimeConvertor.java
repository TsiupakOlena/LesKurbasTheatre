package com.leskurbas.dto;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeConvertor {

    private static final long TIME_ZONE_OFFSET = 1000 * 60 * 60 * 2;
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat("dd-MM-YYYY ");
    private static final SimpleDateFormat INPUT_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMAT =
            new SimpleDateFormat("HH:mm:ss.SSS");

    public static String convertTime(Time time) {
        Time temp = new Time(time.getTime() - TIME_ZONE_OFFSET);
        String resultTime = TIME_FORMAT.format(temp);
        resultTime =  resultTime.substring(0,
                resultTime.lastIndexOf(":"));
        return resultTime;
    }

    public static Time convertString(String time) {
        Time resultTime = null;
        try {
            String timeWithSeconds = time + ":00.000";
            java.util.Date parsedDate = TIME_FORMAT.parse(timeWithSeconds);
            resultTime = new Time(parsedDate.getTime() + TIME_ZONE_OFFSET);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultTime;
    }

    public static String convertDate(Date date) {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    public static Date convertStringToDate(String date) {
        Date resultDate = null;
        try {
            System.out.println(date);
            java.util.Date parsedDate = INPUT_DATE_FORMAT.parse(date);
            System.out.println(parsedDate);
            resultDate = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }
}
