/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.logic.entity;

/**
 * 営業日中日計算用のパラメータエンティティ.
 *
 * @author sis r.iwaki
 */
public class LogicBussinesOutputEntity {

    /**
     * Designated date.
     */
    private String date = null;

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

}
