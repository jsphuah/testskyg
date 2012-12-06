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
import jp.airlink.entity.AptMst;
import jp.airlink.logic.LogicIF;
import jp.airlink.logic.entity.LogicSearchAirPortMasterInputEntity;
import jp.airlink.logic.entity.LogicSearchAirPortMasterOutputEntity;
import jp.airlink.sql.aptMst.SelectWithAptMstDto;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Logic class urban master search.
 *
 * @author sis r.iwaki
 */
public class LogicSearchAirPortMaster implements LogicIF {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(LogicSearchAirPortMaster.class);
    /** Class Results. */
    private LogicSearchAirPortMasterOutputEntity result = null;

    @Override
    public int proc(
        Object input) {

        if (input == null) {
            logger.info("Class parameter is NULL");
            result = new LogicSearchAirPortMasterOutputEntity();
            return 1;
        }

        // Cast in a dedicated entity within its own class
        LogicSearchAirPortMasterInputEntity myEntity = (LogicSearchAirPortMasterInputEntity) input;

        if (myEntity.getAptCd() == null || "".equals(myEntity.getAptCd())) {
            logger.info("parameter \"aptCd\" is NULL");
            result = new LogicSearchAirPortMasterOutputEntity();
            return 1;
        }

        SelectWithAptMstDto selectWithAptMstDto = new SelectWithAptMstDto();
        selectWithAptMstDto.setAptCd(myEntity.getAptCd());

        JdbcManager jdbcManager = (JdbcManager) SingletonS2ContainerFactory.getContainer().getComponent(JdbcManager.class);
        AptMst record = jdbcManager.selectBySqlFile(AptMst.class, SqlFileNameConst.SQL_FILE_NAME_APT_MST, selectWithAptMstDto).getSingleResult();

        if (record == null) {
            logger.info("designation airport code [" + myEntity.getAptCd() + "] is no record");
            result = new LogicSearchAirPortMasterOutputEntity();
            return 1;
        }

        result = new LogicSearchAirPortMasterOutputEntity();

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
