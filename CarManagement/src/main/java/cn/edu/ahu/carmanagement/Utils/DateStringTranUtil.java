package cn.edu.ahu.carmanagement.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: HDC
 * @Description:
 * @Date: Created in 10:11 2018/5/6
 */
public class DateStringTranUtil {

    //Date转字符串
    public static String dateToString(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }

    //字符串转Date
    public static Date stringToDate(String time) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = fmt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    //字符串转Date
    public static Date stringToDate2(String time) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = fmt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}