/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.airlink.common.constants.SqlFileNameConst;
import jp.airlink.common.constants.db.TBL_Const_YMD;
import jp.airlink.common.util.CommonUtil;
import jp.airlink.entity.Ymd;
import jp.airlink.logic.LogicIF;
import jp.airlink.logic.entity.LogicBussinesInputEntity;
import jp.airlink.logic.entity.LogicBussinesOutputEntity;
import jp.airlink.sql.ymd.SelectWithYmdDto;

import org.apache.log4j.Logger;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Based on the parameters, the logic class to calculate the number of business days until the target date.
 *
 * @author sis r.iwaki
 */
public class LogicBusinessDateCalculator implements LogicIF {

    /** Logger definition. */
    private static final Logger logger = Logger.getLogger(LogicBusinessDateCalculator.class);
    /** Class Results. */
    private LogicBussinesOutputEntity result = null;

    @Override
    public int proc(
        Object input) {

        // 自クラス専用エンティティにキャスト
        LogicBussinesInputEntity myEntity = (LogicBussinesInputEntity) input;

        int dayNum = Integer.parseInt(myEntity.getDayNum());

        if (dayNum == 0) {

            result = new LogicBussinesOutputEntity();
            result.setDate(myEntity.getDate());

            return 0;
        }

        int dayLoop = 0; //検索深度（カスタマイズ対応）
        final int dayNumLvl1 = 10;
        final int dayNumLvl2 = 20;
        final int dayNumLvl3 = 30;
        final int dayNumGet1 = 20;
        final int dayNumGet2 = 40;
        final int dayNumGet3 = 60;
        final int dayNumGet4 = 60;
        if (dayNum < dayNumLvl1) {
            dayLoop = dayNumGet1;
        } else if (dayNum <= dayNumLvl2) {
            dayLoop = dayNumGet2;
        } else if (dayNum <= dayNumLvl3) {
            dayLoop = dayNumGet3;
        } else {
            dayLoop = dayNumGet4;
        }

        String tmpDate = myEntity.getDate();

        List<String> ymdList = new ArrayList<>();

        int startcnt = 1;
        if ("1".equals(myEntity.getToday())) {
            startcnt = 0;
        }
        for (int i = startcnt; i <= dayLoop; i++) {
            if ("1".equals(myEntity.getPast())) {
                ymdList.add(CommonUtil.addDay(tmpDate, (-1 * i)));
            } else {
                ymdList.add(CommonUtil.addDay(tmpDate, i));
            }
        }
        SelectWithYmdDto selectWithYmdDto = new SelectWithYmdDto();
        selectWithYmdDto.setYmdList(ymdList);

        String sqlFile = SqlFileNameConst.SQL_FILE_NAME_YMD_LIST_DESC;
        if ("1".equals(myEntity.getPast())) {
            sqlFile = SqlFileNameConst.SQL_FILE_NAME_YMD_LIST_ASC;
        }
        JdbcManager jdbcManager = (JdbcManager) SingletonS2ContainerFactory.getContainer().getComponent(JdbcManager.class);
        List<Ymd> records = jdbcManager.selectBySqlFile(Ymd.class, sqlFile, selectWithYmdDto).getResultList();

        logger.debug("records size " + records.size());

        int cnt = 0;
        String date = null;
        boolean halfDay = false;

        for (int idx = 0; idx < records.size(); idx++) {

            Ymd ymd = records.get(idx);

            //営業日かどうか判断
            if (!(TBL_Const_YMD.SKYG_OFF_KBN_HALF_DAY.equals(ymd.getSkygOffKbn()) || TBL_Const_YMD.SKYG_OFF_KBN_NOT_WORK_DAY.equals(ymd.getSkygOffKbn()))) {
                // 通常営業日
                ++cnt;
            } else if (halfDay && TBL_Const_YMD.SKYG_OFF_KBN_NOT_WORK_DAY.equals(ymd.getSkygOffKbn())) {
                // 半日営業日も含むとき
                ++cnt;
            } else {
                continue;
            }

            if (cnt == dayNum) {
                date = ymd.getYmd();
                break;
            }
        }

        result = new LogicBussinesOutputEntity();
        result.setDate(date);

        return 0;
    }

    @Override
    public Object getResult() {
        return this.result;
    }

}
