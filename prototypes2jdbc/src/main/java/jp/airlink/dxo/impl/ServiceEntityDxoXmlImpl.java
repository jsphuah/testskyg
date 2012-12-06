/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.dxo.impl;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import jp.airlink.service.entity.output.ServiceResponseEntity;

/**
 * Class to convert a string to XML only entity that has been requested service.
 *
 * @author sis r.iwaki
 */
public class ServiceEntityDxoXmlImpl extends ServiceEntityDxoAbst {

    @Override
    protected Object exchangeToObj(
        String rqStr,
        Class<?> clazz) {
        ByteArrayInputStream in = new ByteArrayInputStream(rqStr.getBytes());
        return JAXB.unmarshal(in, clazz);
    }

    @Override
    protected String exchangeToStr(
        ServiceResponseEntity rsObj) {
        StringWriter xml = new StringWriter();
        JAXB.marshal(rsObj, xml);
        return xml.toString();
    }
}
