/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;


/**
 * {@link jp.airlink.service.impl.ServiceSearchAirPortMaster} Input Entity.
 *
 * @author sis r.iwaki
 */
public class AirPortEntity {

    /**
     * Airport Code.
     */
    private String aptCd = null;

    /**
     * get the aptCd.
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
