/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.logic.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import jp.airlink.common.constants.SqlFileNameConst;
import jp.airlink.entity.CntMst;
import jp.airlink.logic.LogicIF;
import jp.airlink.logic.entity.LogicSearchCountryMasterInputEntity;
import jp.airlink.logic.entity.LogicSearchCountryMasterOutputEntity;
import jp.airlink.sql.cntMst.SelectWithCntMstDto;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Logic class country master search.
 *
 * @author sis r.iwaki
 */
public class LogicSearchCountryMaster implements LogicIF {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(LogicSearchCountryMaster.class);
    /** Class Results. */
    private LogicSearchCountryMasterOutputEntity result = null;

    @Override
    public int proc(
        Object input) {

        if (input == null) {
            logger.info("Class parameter is NULL");
            result = new LogicSearchCountryMasterOutputEntity();
            return 1;
        }

        // Cast in a dedicated entity within its own class
        LogicSearchCountryMasterInputEntity myEntity = (LogicSearchCountryMasterInputEntity) input;

        if (myEntity.getCntCd() == null || "".equals(myEntity.getCntCd()) || myEntity.getCntCd().length() != 2) {
            logger.info("parameter \"cntCd\" is NULL");
            result = new LogicSearchCountryMasterOutputEntity();
            return 1;
        }

        SelectWithCntMstDto selectWithCntMstDto = new SelectWithCntMstDto();
        selectWithCntMstDto.setCntCd(myEntity.getCntCd());

        JdbcManager jdbcManager = (JdbcManager) SingletonS2ContainerFactory.getContainer().getComponent(JdbcManager.class);

        CntMst record = jdbcManager.selectBySqlFile(CntMst.class, SqlFileNameConst.SQL_FILE_NAME_CNT_MST, selectWithCntMstDto).getSingleResult();

        if (record == null) {
            logger.info("designation airport code [" + myEntity.getCntCd() + "] is no record");
            result = new LogicSearchCountryMasterOutputEntity();
            return 1;

        }

        result = new LogicSearchCountryMasterOutputEntity();

        Field[] fields = result.getClass().getDeclaredFields();

        for (int m = 0; m < fields.length; m++) {
            Field field = fields[m];
            try {
                String ovOneStr = BeanUtils.getProperty(record, field.getName());
                BeanUtils.setProperty(result, field.getName(), (Object) ovOneStr);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                logger.debug("bean replace exception [" + e.toString() + "] msg [" + e.getMessage() + "]");
                continue;
            }
        }

        return 0;
    }

    @Override
    public Object getResult() {
        return this.result;
    }

}
