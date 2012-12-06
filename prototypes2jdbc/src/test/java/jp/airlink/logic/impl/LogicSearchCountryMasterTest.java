package jp.airlink.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.airlink.logic.entity.LogicSearchCountryMasterInputEntity;
import jp.airlink.logic.entity.LogicSearchCountryMasterOutputEntity;

import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

@RunWith(Seasar2.class)
public class LogicSearchCountryMasterTest {

    private TestContext ctx;
    private LogicSearchCountryMaster logicSearchCountryMaster;

    public final void scenario1() {

        LogicSearchCountryMasterInputEntity input = null;
        if (logicSearchCountryMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario2() {

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        if (logicSearchCountryMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario2_2() {

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        input.setCntCd("");
        if (logicSearchCountryMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario3() {

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        input.setCntCd("N");
        if (logicSearchCountryMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario4() {

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        input.setCntCd("BBB");
        if (logicSearchCountryMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario5() {

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        input.setCntCd("ZZ");
        if (logicSearchCountryMaster.proc(input) == 0) {
            fail();
        }
    }

    public final void scenario6() {

        LogicSearchCountryMasterInputEntity input = new LogicSearchCountryMasterInputEntity();
        input.setCntCd("JP");
        if (logicSearchCountryMaster.proc(input) != 0) {
            fail();
        }
        LogicSearchCountryMasterOutputEntity output = (LogicSearchCountryMasterOutputEntity) logicSearchCountryMaster.getResult();
        assertEquals("JP", output.getCntCd());
    }

}
