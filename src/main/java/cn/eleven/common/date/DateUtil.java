package cn.eleven.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: eleven
 * @date: 2018/9/7 19:43
 * @description: 日期类型工具
 */
public class DateUtil {

    public static final int PATTEN_TO_DAY = 1;
    public static final int PATTEN_TO_SECOND = 2;
    public static SimpleDateFormat sdfToDay = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdfToSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String errMsg = "转换失败，时间参数格式错误";


    public static Date toDate(String timeStr, int patten) {
        Date result = null;
        try {
            if (patten == PATTEN_TO_DAY) {
                result = sdfToDay.parse(timeStr);
            } else {
                result = sdfToSecond.parse(timeStr);
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(errMsg);
        }
        return result;
    }

    public static String toString(Date date, int patten) {
        String result = null;
        try {
            if (patten == PATTEN_TO_DAY) {
                result = sdfToDay.format(date);
            } else {
                result = sdfToSecond.format(date);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(errMsg);
        }

        return result;
    }

    /**
     * 将second类型转为标准的格式时间
     * @param second
     * @param patten
     * @return
     */
    public static String toStandardFormatString(long second, int patten) {
        Date date = new Date(second);
        return toString(date, patten);
    }

}
