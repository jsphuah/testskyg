/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;


/**
 * {@link jp.airlink.service.impl.ServiceBusinessDateCalculation} Input Entity.
 *
 * @author sis r.iwaki
 */
public class BussinesEntity {

    /**
     * Designated date.
     */
    private String date = null;
    /**
     * From a specified date, within days looking at n (search depth).
     */
    private String dayNum = null;
    /**
     * Whether retroactive.
     */
    private String past = null;
    /**
     * Whether or not to include any specified day.
     */
    private String today = null;

    /**
     * get the date.
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date Set the date.
     */
    public void setDate(
        String date) {
        this.date = date;
    }

    /**
     * get the dayNum.
     *
     * @return dayNum
     */
    public String getDayNum() {
        return dayNum;
    }

    /**
     * @param dayNum Set the dayNum.
     */
    public void setDayNum(
        String dayNum) {
        this.dayNum = dayNum;
    }

    /**
     * get the past.
     *
     * @return past
     */
    public String getPast() {
        return past;
    }

    /**
     * @param past Set the past.
     */
    public void setPast(
        String past) {
        this.past = past;
    }

    /**
     * get the today.
     *
     * @return today
     */
    public String getToday() {
        return today;
    }

    /**
     * @param today Set the today.
     */
    public void setToday(
        String today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "BussinesEntity [date=" + date + ", day_num=" + dayNum + ", past=" + past + ", today=" + today + "]";
    }

}
