/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;

/**
 * {@link jp.airlink.service.impl.ServiceSearchCountryMaster} Input Entity.
 *
 * @author sis r.iwaki
 */
public class CountryEntity {

    /**
     * Country code.
     */
    private String cntCd = null;

    /**
     * get the cntCd.
     *
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
