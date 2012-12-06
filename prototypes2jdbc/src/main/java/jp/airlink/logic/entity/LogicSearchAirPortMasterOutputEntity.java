/*
 * AirLink Java Modure
 *
 * Created by   :   r.iwaki
 * Created on   :   2013/06/XX
 */
package jp.airlink.logic.entity;

/**
 * {@link jp.airlink.logic.impl.LogicSearchAirPortMaster} Output Entity.
 *
 * @author sis r.iwaki
 */
public class LogicSearchAirPortMasterOutputEntity {

    /**
     * 空港コード.
     */
    private String aptCd = null;

    /**
     * 空港コードを取得します.
     *
     * @return 空港コード
     */
    public String getAptCd() {
        return aptCd;
    }

    /**
     * 空港コードを設定します.
     *
     * @param aptCd 空港コード
     */
    public void setAptCd(
        String aptCd) {
        this.aptCd = aptCd;
    }

    /**
     * 空港名称（カナ）.
     */
    private String aptNmk = null;

    /**
     * 空港名称（カナ）を取得します.
     *
     * @return 空港名称（カナ）
     */
    public String getAptNmk() {
        return aptNmk;
    }

    /**
     * 空港名称（カナ）を設定します.
     *
     * @param aptNmk 空港名称（カナ）
     */
    public void setAptNmk(
        String aptNmk) {
        this.aptNmk = aptNmk;
    }

}
