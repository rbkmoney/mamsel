package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyTerminalPaymentProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentTerminal;
import com.rbkmoney.damsel.domain.PaymentTerminalConditionDefinition;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.TerminalPaymentUtil.getTerminalPaymentProviderName;
import static com.rbkmoney.mamsel.TerminalPaymentUtil.isSetTerminalPaymentProvider;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerminalPaymentUtilTest {

    @Test
    void getTerminalPaymentProviderNameTest_PaymentTerminal() {
        PaymentTerminal nullTerminal = null;
        assertThrows(NullPointerException.class, () -> getTerminalPaymentProviderName(nullTerminal));

        PaymentTerminal terminal = new PaymentTerminal();
        assertTrue(getTerminalPaymentProviderName(terminal).isEmpty());

        terminal.setPaymentService(new PaymentServiceRef(REF));
        assertEquals(REF, getTerminalPaymentProviderName(terminal).get());

        terminal.setPaymentService(null);
        terminal.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(LegacyTerminalPaymentProvider.uzcard.name(), getTerminalPaymentProviderName(terminal).get());

        terminal.setPaymentService(new PaymentServiceRef(REF));
        terminal.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(REF, getTerminalPaymentProviderName(terminal).get());
    }

    @Test
    void getTerminalPaymentProviderNameTest_PaymentTerminalConditionDefinition() {
        PaymentTerminalConditionDefinition nullDefinition = null;
        assertThrows(NullPointerException.class, () -> getTerminalPaymentProviderName(nullDefinition));

        PaymentTerminalConditionDefinition definition = new PaymentTerminalConditionDefinition();
        assertTrue(getTerminalPaymentProviderName(definition).isEmpty());

        definition.setPaymentServiceIs(new PaymentServiceRef(REF));
        assertEquals(REF, getTerminalPaymentProviderName(definition).get());

        definition.setProviderIsDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(LegacyTerminalPaymentProvider.uzcard.name(), getTerminalPaymentProviderName(definition).get());
    }

    @Test
    void getTerminalPaymentProviderNameTest() {
        assertTrue(getTerminalPaymentProviderName(null, null).isEmpty());
        assertTrue(getTerminalPaymentProviderName(new PaymentServiceRef(), null).isEmpty());
        assertTrue(getTerminalPaymentProviderName(new PaymentServiceRef(EMPTY), null).isEmpty());
        assertEquals(
                REF,
                getTerminalPaymentProviderName(new PaymentServiceRef(REF), null).get()
        );
        assertEquals(
                REF,
                getTerminalPaymentProviderName(new PaymentServiceRef(REF), LegacyTerminalPaymentProvider.uzcard).get()
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(null, LegacyTerminalPaymentProvider.uzcard).get()
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(new PaymentServiceRef(), LegacyTerminalPaymentProvider.uzcard).get()
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(new PaymentServiceRef(EMPTY), LegacyTerminalPaymentProvider.uzcard).get()
        );
    }

    @Test
    void isSetTerminalPaymentProviderTest_PaymentTerminal() {
        PaymentTerminal nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTerminalPaymentProvider(nullObj));

        PaymentTerminal method = new PaymentTerminal();
        assertFalse(isSetTerminalPaymentProvider(method));

        method.setPaymentService(new PaymentServiceRef(REF));
        assertTrue(isSetTerminalPaymentProvider(method));

        method = new PaymentTerminal();
        method.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertTrue(isSetTerminalPaymentProvider(method));
    }

    @Test
    void isSetTerminalPaymentProviderTest_PaymentTerminalConditionDefinition() {
        PaymentTerminalConditionDefinition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTerminalPaymentProvider(nullObj));

        PaymentTerminalConditionDefinition definition = new PaymentTerminalConditionDefinition();
        assertFalse(isSetTerminalPaymentProvider(definition));

        definition.setPaymentServiceIs(new PaymentServiceRef(REF));
        assertTrue(isSetTerminalPaymentProvider(definition));

        definition = new PaymentTerminalConditionDefinition();
        definition.setProviderIsDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertTrue(isSetTerminalPaymentProvider(definition));
    }
}
