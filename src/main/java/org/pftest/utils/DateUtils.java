package org.pftest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
    private DateUtils() {
        super();
    }

    /**
     * @return Get current date of the machine in the default structure
     */
    public static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }

    /**
     * @return Get current date and time of the machine in the structure dd/MM/yyyy HH:mm:ss
     */
    public static String getCurrentDateTime() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(now);
    }

    /**
     *
     * @param separator_Character character to separate the date and time
     * @return Get current date and time of the machine in the custom structure
     */
    public static String getCurrentDateTimeCustom(String separator_Character) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String timeStamp = formatter.format(now).replace("/", separator_Character);
        timeStamp = timeStamp.replace(" ", separator_Character);
        timeStamp = timeStamp.replace(":", separator_Character);
        return timeStamp;
    }
}
