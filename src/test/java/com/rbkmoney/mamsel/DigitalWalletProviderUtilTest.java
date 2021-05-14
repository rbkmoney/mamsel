package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyDigitalWalletProvider;
import com.rbkmoney.damsel.domain.DigitalWallet;
import com.rbkmoney.damsel.domain.DigitalWalletConditionDefinition;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentMethod;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.DigitalWalletUtil.getDigitalWalletName;
import static com.rbkmoney.mamsel.DigitalWalletUtil.isSetDigitalWallet;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DigitalWalletProviderUtilTest {

    @Test
    void getDigitalWalletNameTest_DigitalWallet() {
        DigitalWallet nullWalltet = null;
        assertThrows(NullPointerException.class, () -> getDigitalWalletName(nullWalltet));

        DigitalWallet wallet = new DigitalWallet();
        assertTrue(getDigitalWalletName(wallet).isEmpty());

        wallet.setPaymentService(new PaymentServiceRef(REF));
        assertEquals(REF, getDigitalWalletName(wallet).get());

        wallet.setPaymentService(null);
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertEquals(LegacyDigitalWalletProvider.rbkmoney.name(), getDigitalWalletName(wallet).get());

        wallet.setPaymentService(new PaymentServiceRef(REF));
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertEquals(REF, getDigitalWalletName(wallet).get());
    }

    @Test
    void getDigitalWalletNameTest_DigitalWalletConditionDefinition() {
        DigitalWalletConditionDefinition nullDefinition = null;
        assertThrows(NullPointerException.class, () -> getDigitalWalletName(nullDefinition));

        DigitalWalletConditionDefinition definition = new DigitalWalletConditionDefinition();
        assertTrue(getDigitalWalletName(definition).isEmpty());

        definition.setPaymentServiceIs(new PaymentServiceRef(REF));
        assertEquals(REF, getDigitalWalletName(definition).get());

        definition.setProviderIsDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertEquals(LegacyDigitalWalletProvider.rbkmoney.name(), getDigitalWalletName(definition).get());
    }

    @Test
    void getDigitalWalletNameTest() {
        assertTrue(getDigitalWalletName(null, null).isEmpty());
        assertTrue(getDigitalWalletName(new PaymentServiceRef(), null).isEmpty());
        assertTrue(getDigitalWalletName(new PaymentServiceRef(EMPTY), null).isEmpty());
        assertEquals(
                REF,
                getDigitalWalletName(new PaymentServiceRef(REF), null).get()
        );
        assertEquals(
                REF,
                getDigitalWalletName(new PaymentServiceRef(REF), LegacyDigitalWalletProvider.rbkmoney).get()
        );
        assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                getDigitalWalletName(null, LegacyDigitalWalletProvider.rbkmoney).get()
        );
        assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                getDigitalWalletName(new PaymentServiceRef(), LegacyDigitalWalletProvider.rbkmoney).get()
        );
        assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                getDigitalWalletName(new PaymentServiceRef(EMPTY), LegacyDigitalWalletProvider.rbkmoney).get()
        );
    }

    @Test
    void isSetDigitalWalletTest_DigitalWallet() {
        DigitalWallet nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetDigitalWallet(nullObj));

        DigitalWallet wallet = new DigitalWallet();
        assertFalse(isSetDigitalWallet(wallet));

        wallet.setPaymentService(new PaymentServiceRef(REF));
        assertTrue(isSetDigitalWallet(wallet));

        wallet = new DigitalWallet();
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertTrue(isSetDigitalWallet(wallet));
    }

    @Test
    void isSetDigitalWalletTest_DigitalWalletConditionDefinition() {
        DigitalWalletConditionDefinition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetDigitalWallet(nullObj));

        DigitalWalletConditionDefinition definition = new DigitalWalletConditionDefinition();
        assertFalse(isSetDigitalWallet(definition));

        definition.setPaymentServiceIs(new PaymentServiceRef(REF));
        assertTrue(isSetDigitalWallet(definition));

        definition = new DigitalWalletConditionDefinition();
        definition.setProviderIsDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertTrue(isSetDigitalWallet(definition));
    }
}
