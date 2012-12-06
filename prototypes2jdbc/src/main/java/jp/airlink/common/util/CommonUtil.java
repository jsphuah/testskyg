/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jp.airlink.common.constants.Const;

/**
 * Class set of general-purpose utility functions super.
 *
 * @author sis r.iwaki
 */
public class CommonUtil {

    /**
     * Byte indicates the start of the "year" YYYYMMDD format.
     */
    public static final int DATE_YEAR_STR_POSITION = 0;
    /**
     * Byte indicates the start of the "Year" calendar class.
     */
    public static final int CALENDER_YEAR_START = 0;
    /**
     * Byte indicates the end of the "year" calendar class.
     */
    public static final int CALENDER_YEAR_END = 4;
    /**
     * Indicates the starting byte of the "month" calendar class.
     */
    public static final int CALENDER_MONTH_START = 5;
    /**
     * Byte indicates the end of the "month" calendar class.
     */
    public static final int CALENDER_MONTH_END = 7;
    /**
     * Indicates the starting byte of the "day" calendar class.
     */
    public static final int CALENDER_DAY_START = 8;
    /**
     * Byte indicates the end of the "day" calendar class.
     */
    public static final int CALENDER_DAY_END = 10;
    /**
     * Indicates the starting byte of the "time" HHMM format.
     */
    public static final int TIME_HOUR_STR_POSITION = 0;
    /**
     * Byte indicates the end of the "time" HHMM format.
     */
    public static final int TIME_HOUR_END_POSITION = 2;
    /**
     * Byte indicates the end of the "minute" HHMM format.
     */
    public static final int TIME_MINUTE_END_POSITION = 4;
    /**
     * Byte indicates the end of the "date" YYYYMMDD format.
     */
    public static final int DATE_DAY_END_POSITION = 8;
    /**
     * Byte indicates the end of the "month" YYYYMMDD format.
     */
    public static final int DATE_MONTH_END_POSITION = 6;
    /**
     * Byte indicates the end of the "year" YYYYMMDD format.
     */
    public static final int DATE_YEAR_END_POSITION = 4;

    /**
     * Date of addition<BR>
     * On error, returns an empty string.
     *
     * @param datevalue Date string (YYYYMMDD)
     * @param addValue Plus the number of days
     * @return String Date string (YYYYMMDD)
     */
    public static String addDay(
        String datevalue,
        long addValue) {

        String rtn = Const.EMPTY_STR;

        try {
            if (datevalue == null) {
                return rtn;
            }

            final int dataValueLength = 8;
            if ((datevalue.length() != dataValueLength)) {
                return rtn;
            }
            Calendar cl = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            Date dd = new Date();

            cl.set(Integer.parseInt(datevalue.substring(DATE_YEAR_STR_POSITION, DATE_YEAR_END_POSITION)),
                    Integer.parseInt(datevalue.substring(DATE_YEAR_END_POSITION, DATE_MONTH_END_POSITION)) - 1,
                    Integer.parseInt(datevalue.substring(DATE_MONTH_END_POSITION, DATE_DAY_END_POSITION)));
            cl.add(Calendar.DATE, (int) addValue);
            dd = cl.getTime();
            rtn = df.format(dd);
            rtn = rtn.substring(CALENDER_YEAR_START, CALENDER_YEAR_END)
                    + rtn.substring(CALENDER_MONTH_START, CALENDER_MONTH_END)
                    + rtn.substring(CALENDER_DAY_START, CALENDER_DAY_END);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtn;
    }

    /**
     * To get the system date<BR>
     * On error, returns an empty string.
     *
     * @param dateformat Date format string (ex:yyyyMMdd)
     * @return String System date
     */
    public static String getSystemDate(
        String dateformat) {
        String retStr = "";
        try {

            if (dateformat == null) {
                return retStr;
            }

            if (dateformat.length() != 0) {
                SimpleDateFormat sd = new SimpleDateFormat(dateformat);
                Date dt = new Date();
                retStr = sd.format(dt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retStr;
    }

    /**
     * (To determine whether empty null / trim) the argument is an empty string.
     *
     * @param str To be determined
     * @return is empty: true
     */
    public static boolean isEmpty(
        String str) {
        return isEmpty(str, true);
    }

    /**
     * (Determining whether "" as null / trim) the argument is an empty string.
     *
     * @param str To be determined
     * @param trim whether or not to trim
     * @return is empty: true
     */
    public static boolean isEmpty(
        String str,
        boolean trim) {
        boolean ret = false;
        if (str == null) {
            ret = true;
        } else {
            String tmp = new String(str);
            if (trim) {
                tmp = tmp.trim();
            }
            if (tmp.equals("")) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * Numerical determination of a string.
     *
     * @param checkStr The string to determine whether numerical
     * @return boolean TRUE, if it is not recognized as a numeric value: FALSE if it is recognized as a number
     */
    public static boolean checkNumber(
        String checkStr) {
        return checkNumber(checkStr, null);
    }

    /**
     * Numerical determination of a string.
     * <P>
     *
     * @param checkStr The string to determine whether numerical
     * @param except Allowed any non-numeric string
     * @return boolean TRUE, if it is not recognized as a numeric value: FALSE if it is recognized as a number
     */
    public static boolean checkNumber(
        String checkStr,
        char[] except) {
        if ((checkStr == null) || (checkStr.equals(""))) {
            return false;
        }
        int len = checkStr.length();
        LBL_NEXT_CHAR: for (int idx = 0; idx < len; idx++) {
            char chr = checkStr.charAt(idx);
            if (('0' <= chr) && (chr <= '9')) {
                continue;
            }
            if (except == null) {
                return false;
            }
            for (int i = 0; i < except.length; i++) {
                char check = except[i];
                if (check == chr) {
                    continue LBL_NEXT_CHAR;
                }
            }
            return false;
        }
        return true;
    }

}
