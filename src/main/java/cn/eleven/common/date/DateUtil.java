package cn.eleven.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.eleven.common.date.DateUtil.DatePatten.PATTEN_TO_DAY;

/**
 * @author eleven
 * @date 2018/9/7
 * @description 日期类型工具
 */
public class DateUtil {

    public static SimpleDateFormat sdfToDay = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdfToSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String errMsg = "转换失败，时间参数格式错误";

    /**
     * 转换格式常量
     */
    public enum DatePatten{
        /**
         * 转换到天
         */
        PATTEN_TO_DAY,
        /**
         * 转换到秒
         */
        PATTEN_TO_SECOND
    }

    public static Date toDate(String timeStr,DatePatten patten) {
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

    public static String toString(Date date, DatePatten patten) {
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
     * 获取当前时间并且格式化
     * @param patten
     * @return
     */
    public static Date getCurrentDate(DatePatten patten){
        return toDate(toString(new Date(),patten),patten);
    }
    /**
     * 获取当前时间并且格式化
     * @param patten
     * @return
     */
    public static String getCurrentDateString(DatePatten patten){
        return toString(new Date(),patten);
    }

    /**
     * 将second类型转为标准的格式时间
     * @param second
     * @param patten
     * @return
     */
    public static String toStandardFormatString(long second, DatePatten patten) {
        Date date = new Date(second);
        return toString(date, patten);
    }

}
