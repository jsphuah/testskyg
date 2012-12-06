/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.sql.ymd;

import java.util.List;

/**
 * The class that provides the dynamic parameters that you specify when for YMD, execution SQL.
 *
 * @author sis r.iwaki
 */
public class SelectWithYmdDto {

    /**
     * List of target date.
     */
    private List<String> ymdList = null;

    /**
     * get the ymdList.
     *
     * @return ymdList
     */
    public List<String> getYmdList() {
        return ymdList;
    }

    /**
     * @param ymdList Set the ymdList.
     */
    public void setYmdList(
        List<String> ymdList) {
        this.ymdList = ymdList;
    }

}
