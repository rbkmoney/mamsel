package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyTerminalPaymentProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentTerminal;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.TerminalPaymentUtil.getTerminalPaymentProviderName;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class TerminalPaymentUtilTest {

    @Test
    void getTerminalPaymentProviderNameTest_PaymentTerminal() {
        PaymentTerminal nullTerminal = null;
        assertThrows(NullPointerException.class, () -> getTerminalPaymentProviderName(nullTerminal));

        PaymentTerminal terminal = new PaymentTerminal();
        assertNull(getTerminalPaymentProviderName(terminal));

        terminal.setPaymentService(new PaymentServiceRef(REF));
        assertEquals(REF, getTerminalPaymentProviderName(terminal));

        terminal.setPaymentService(null);
        terminal.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(LegacyTerminalPaymentProvider.uzcard.name(), getTerminalPaymentProviderName(terminal));

        terminal.setPaymentService(new PaymentServiceRef(REF));
        terminal.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(REF, getTerminalPaymentProviderName(terminal));
    }

    @Test
    void getTerminalPaymentProviderNameTest() {
        assertNull(getTerminalPaymentProviderName(null, null));
        assertNull(getTerminalPaymentProviderName(new PaymentServiceRef(), null));
        assertNull(getTerminalPaymentProviderName(new PaymentServiceRef(EMPTY), null));
        assertEquals(
                REF,
                getTerminalPaymentProviderName(new PaymentServiceRef(REF), null)
        );
        assertEquals(
                REF,
                getTerminalPaymentProviderName(new PaymentServiceRef(REF), LegacyTerminalPaymentProvider.uzcard)
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(null, LegacyTerminalPaymentProvider.uzcard)
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(new PaymentServiceRef(), LegacyTerminalPaymentProvider.uzcard)
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(new PaymentServiceRef(EMPTY), LegacyTerminalPaymentProvider.uzcard)
        );
    }
}
