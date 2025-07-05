package com.manager.account.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String yyyymmddhhmmss = "dd/MM/yyyy hh:mm:ss";

    public static String convertToString(Date date, String format) {
        SimpleDateFormat dt1 = new SimpleDateFormat(format);
        return dt1.format(date);
    }
}
