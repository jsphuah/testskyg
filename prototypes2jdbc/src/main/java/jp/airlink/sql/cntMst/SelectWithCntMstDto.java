/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.sql.cntMst;


/**
 * The class that provides the dynamic parameters that you specify when against CNT_MST, execution SQL.
 *
 * @author ryosuke
 */
public class SelectWithCntMstDto {

    /**
     * Country Code.
     */
    private String cntCd = null;

    /**
     * get the cntCd.
     * @return cntCd
     */
    public String getCntCd() {
        return cntCd;
    }

    /**
     * @param cntCd Set the cntCd.
     */
    public void setCntCd(
        String cntCd) {
        this.cntCd = cntCd;
    }
}
