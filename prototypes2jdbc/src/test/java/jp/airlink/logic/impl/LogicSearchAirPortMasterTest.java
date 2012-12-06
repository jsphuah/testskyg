package jp.airlink.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.airlink.logic.entity.LogicSearchAirPortMasterInputEntity;
import jp.airlink.logic.entity.LogicSearchAirPortMasterOutputEntity;

import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

@RunWith(Seasar2.class)
public class LogicSearchAirPortMasterTest {

    private TestContext ctx;
    private LogicSearchAirPortMaster logicSearchAirPortMaster;

    public final void scenario1() {

        LogicSearchAirPortMasterInputEntity input = null;
        if (logicSearchAirPortMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario2() {

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        if (logicSearchAirPortMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario2_2() {

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        input.setAptCd("");
        if (logicSearchAirPortMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario3() {

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        input.setAptCd("NR");
        if (logicSearchAirPortMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario4() {

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        input.setAptCd("BBBB");
        if (logicSearchAirPortMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario5() {

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        input.setAptCd("ZZZ");
        if (logicSearchAirPortMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario6() {

        LogicSearchAirPortMasterInputEntity input = new LogicSearchAirPortMasterInputEntity();
        input.setAptCd("NRT");
        if (logicSearchAirPortMaster.proc(input) != 0) {
            fail();
        }
        LogicSearchAirPortMasterOutputEntity output = (LogicSearchAirPortMasterOutputEntity) logicSearchAirPortMaster.getResult();
        assertEquals("NRT", output.getAptCd());
    }

}
