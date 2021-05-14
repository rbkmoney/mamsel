package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyMobileOperator;
import com.rbkmoney.damsel.domain.MobileCommerce;
import com.rbkmoney.damsel.domain.MobileCommerceConditionDefinition;
import com.rbkmoney.damsel.domain.MobileOperatorRef;
import com.rbkmoney.damsel.domain.PaymentMethod;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.MobileOperatorUtil.isSetMobileOperatorName;
import static com.rbkmoney.mamsel.MobileOperatorUtil.getMobileOperatorName;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MobileOperatorUtilTest {

    @Test
    void getMobileOperatorNameTest_MobileCommerce() {
        MobileCommerce nullCommerce = null;
        assertThrows(NullPointerException.class, () -> MobileOperatorUtil.getMobileOperatorName(nullCommerce));

        MobileCommerce commerce = new MobileCommerce();
        assertTrue(MobileOperatorUtil.getMobileOperatorName(commerce).isEmpty());

        commerce.setOperator(new MobileOperatorRef(REF));
        assertEquals(REF, MobileOperatorUtil.getMobileOperatorName(commerce).get());

        commerce.setOperator(null);
        commerce.setOperatorDeprecated(LegacyMobileOperator.mts);
        assertEquals(LegacyMobileOperator.mts.name(), MobileOperatorUtil.getMobileOperatorName(commerce).get());

        commerce.setOperator(new MobileOperatorRef(REF));
        commerce.setOperatorDeprecated(LegacyMobileOperator.mts);
        assertEquals(REF, MobileOperatorUtil.getMobileOperatorName(commerce).get());
    }

    @Test
    void getMobileOperatorNameTest_MobileCommerceConditionDefinition() {
        MobileCommerceConditionDefinition nullDefinition = null;
        assertThrows(NullPointerException.class, () -> MobileOperatorUtil.getMobileOperatorName(nullDefinition));

        MobileCommerceConditionDefinition definition = new MobileCommerceConditionDefinition();
        assertTrue(MobileOperatorUtil.getMobileOperatorName(definition).isEmpty());

        definition.setOperatorIs(new MobileOperatorRef(REF));
        assertEquals(REF, MobileOperatorUtil.getMobileOperatorName(definition).get());

        definition.setOperatorIsDeprecated(LegacyMobileOperator.mts);
        assertEquals(LegacyMobileOperator.mts.name(), MobileOperatorUtil.getMobileOperatorName(definition).get());
    }

    @Test
    void getMobileOperatorNameTest() {
        assertTrue(getMobileOperatorName(null, null).isEmpty());
        assertTrue(getMobileOperatorName(new MobileOperatorRef(), null).isEmpty());
        assertTrue(getMobileOperatorName(new MobileOperatorRef(EMPTY), null).isEmpty());
        assertEquals(
                REF,
                getMobileOperatorName(new MobileOperatorRef(REF), null).get()
        );
        assertEquals(
                REF,
                getMobileOperatorName(new MobileOperatorRef(REF), LegacyMobileOperator.mts).get()
        );
        assertEquals(
                LegacyMobileOperator.mts.name(),
                getMobileOperatorName(null, LegacyMobileOperator.mts).get()
        );
        assertEquals(
                LegacyMobileOperator.mts.name(),
                getMobileOperatorName(new MobileOperatorRef(), LegacyMobileOperator.mts).get()
        );
        assertEquals(
                LegacyMobileOperator.mts.name(),
                getMobileOperatorName(new MobileOperatorRef(EMPTY), LegacyMobileOperator.mts).get()
        );
    }

    @Test
    void isSetMobileOperatorTest_MobileCommerce() {
        MobileCommerce nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetMobileOperatorName(nullObj));

        MobileCommerce commerce = new MobileCommerce();
        assertFalse(isSetMobileOperatorName(commerce));

        commerce.setOperator(new MobileOperatorRef(REF));
        assertTrue(isSetMobileOperatorName(commerce));

        commerce = new MobileCommerce();
        commerce.setOperatorDeprecated(LegacyMobileOperator.mts);
        assertTrue(isSetMobileOperatorName(commerce));
    }

    @Test
    void isSetMobileOperatorTest_MobileCommerceConditionDefinition() {
        MobileCommerceConditionDefinition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetMobileOperatorName(nullObj));

        MobileCommerceConditionDefinition definition = new MobileCommerceConditionDefinition();
        assertFalse(isSetMobileOperatorName(definition));

        definition.setOperatorIs(new MobileOperatorRef(REF));
        assertTrue(isSetMobileOperatorName(definition));

        definition = new MobileCommerceConditionDefinition();
        definition.setOperatorIsDeprecated(LegacyMobileOperator.mts);
        assertTrue(isSetMobileOperatorName(definition));
    }
}
