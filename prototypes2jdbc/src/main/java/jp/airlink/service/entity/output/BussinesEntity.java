/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.output;


/**
 * {@link jp.airlink.service.impl.ServiceBusinessDateCalculation} Output Entity.
 *
 * @author sis r.iwaki
 */
public class BussinesEntity {

    /** Result date. */
    private String date = null;

    /**
     * get the date.
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
