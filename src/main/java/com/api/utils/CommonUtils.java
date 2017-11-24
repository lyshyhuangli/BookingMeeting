package com.api.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils
{

    /**
     * 获得指定日期的后一天
     *
     * @param i
     * @return
     */
    public static String getSpecifiedDayAfter(int i)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + i);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

}
