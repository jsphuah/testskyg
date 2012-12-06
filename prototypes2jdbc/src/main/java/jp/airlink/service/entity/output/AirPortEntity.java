/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.output;

/**
 * {@link jp.airlink.service.impl.ServiceSearchAirPortMaster} Output Entity.
 *
 * @author sis r.iwaki
 */
public class AirPortEntity {

    /**
     * Airport Code.
     */
    private String aptCd = null;
    /**
     * Airport name (kana).
     */
    private String aptNmk = null;

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

    /**
     * get the aptNmk.
     *
     * @return aptNmk
     */
    public String getAptNmk() {
        return aptNmk;
    }

    /**
     * @param aptNmk Set the aptNmk.
     */
    public void setAptNmk(
        String aptNmk) {
        this.aptNmk = aptNmk;
    }

}
