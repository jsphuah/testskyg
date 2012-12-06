/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.sql.aptMst;

/**
 * The class that provides the dynamic parameters that you specify when against APT_MST, execution SQL.
 *
 * @author ryosuke
 */
public class SelectWithAptMstDto {

    /**
     * Airport Code.
     */
    private String aptCd = null;

    /**
     * get the aptCd.
     *
     * @return aptCd
     */
    public String getAptCd() {
        return aptCd;
    }

    /**
     * @param aptCd Set the aptCd.
     */
    public void setAptCd(
        String aptCd) {
        this.aptCd = aptCd;
    }

}
