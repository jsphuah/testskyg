/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import jp.airlink.logic.LogicIF;
import jp.airlink.logic.entity.LogicSearchCountryMasterInputEntity;
import jp.airlink.logic.entity.LogicSearchCountryMasterOutputEntity;
import jp.airlink.logic.impl.LogicSearchCountryMaster;
import jp.airlink.service.entity.input.CountryEntity;
import jp.airlink.service.entity.input.ServiceRequestEntity;
import jp.airlink.service.entity.input.ServiceRequestSearchCountryMasterEntity;
import jp.airlink.service.entity.output.ServiceResponseEntity;
import jp.airlink.service.entity.output.ServiceResponseSearchCountryMasterEntity;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Class of service based on the parameters, locate the master country, and returns the result.
 *
 * @author sis r.iwaki
 */
public class ServiceSearchCountryMaster extends ServiceMain {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(ServiceSearchCountryMaster.class);

    @Override
    public String getServiceCode() {
        return "OTHER_02";
    }

    @Override
    public String getServiceName() {
        return "SearchCountryMaster";
    }

    @Override
    protected ServiceResponseEntity eachProc(
        ServiceRequestEntity svRq) {

        ServiceRequestSearchCountryMasterEntity mySvRq = (ServiceRequestSearchCountryMasterEntity) svRq;
        CountryEntity bussines = mySvRq.getCountry();

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        input.setCntCd(bussines.getCntCd());

        LogicIF busLogic = (LogicIF) SingletonS2ContainerFactory.getContainer().getComponent(LogicSearchCountryMaster.class);

        int rslt = busLogic.proc(input);

        logger.debug(" svRq.ClientId [" + svRq.getClientId() + "] svRq.RequestId [" + svRq.getRequestId() + "] rslt [" + rslt + "] ");

        LogicSearchCountryMasterOutputEntity result = (LogicSearchCountryMasterOutputEntity) busLogic.getResult();

        jp.airlink.service.entity.output.CountryEntity rsBus = new jp.airlink.service.entity.output.CountryEntity();

        Field[] fields = rsBus.getClass().getDeclaredFields();

        for (int m = 0; m < fields.length; m++) {
            Field field = fields[m];
            try {
                String ovOneStr = BeanUtils.getProperty(result, field.getName());
                BeanUtils.setProperty(rsBus, field.getName(), (Object) ovOneStr);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                logger.debug("bean replace exception [" + e.toString() + "] msg [" + e.getMessage() + "]");
                continue;
            }
        }
        ServiceResponseSearchCountryMasterEntity svRs = new ServiceResponseSearchCountryMasterEntity();
        svRs.setCountry(rsBus);

        return svRs;
    }

    @Override
    protected boolean check(
        ServiceRequestEntity svRq) {

        ServiceRequestSearchCountryMasterEntity myRqObj = (ServiceRequestSearchCountryMasterEntity) svRq;
        CountryEntity busRqObj = myRqObj.getCountry();

        if (busRqObj.getCntCd() == null || "".equals(busRqObj.getCntCd()) || busRqObj.getCntCd().length() != 2) {
            logger.info("code value [" + busRqObj.getCntCd() + "] is illegality");
            return false;
        }

        return true;
    }

}
