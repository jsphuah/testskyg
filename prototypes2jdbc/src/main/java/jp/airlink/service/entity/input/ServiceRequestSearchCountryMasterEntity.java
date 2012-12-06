/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.entity.input;


/**
 * サービスリクエスト項目用のエンティティ.
 *
 * @author sis r.iwaki
 */
public class ServiceRequestSearchCountryMasterEntity extends ServiceRequestEntity {

    /** {@link CountryEntity}. */
    private CountryEntity country = null;

    /**
     * get the country.
     * @return country
     */
    public CountryEntity getCountry() {
        return country;
    }

    /**
     * @param country Set the country.
     */
    public void setCountry(
        CountryEntity country) {
        this.country = country;
    }



}
