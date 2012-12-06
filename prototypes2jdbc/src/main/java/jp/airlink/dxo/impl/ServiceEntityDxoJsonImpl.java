/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.dxo.impl;

import jp.airlink.service.entity.output.ServiceResponseEntity;

import com.google.gson.Gson;

/**
 * Class to convert a JSON string to a private entity that has been requested service.
 *
 * @author sis r.iwaki
 */
public class ServiceEntityDxoJsonImpl extends ServiceEntityDxoAbst {

    @Override
    protected Object exchangeToObj(
        String rqStr,
        Class<?> clazz) {
        return new Gson().fromJson(rqStr, clazz);
    }

    @Override
    protected String exchangeToStr(
        ServiceResponseEntity rsObj) {
        return new Gson().toJson(rsObj);
    }

}
