package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.DigitalWallet;
import com.rbkmoney.damsel.domain.LegacyDigitalWalletProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.DigitalWalletUtil.getDigitalWalletName;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class DigitalWalletProviderUtilTest {

    @Test
    void getDigitalWalletNameTest_DigitalWallet() {
        DigitalWallet nullWalltet = null;
        assertThrows(NullPointerException.class, () -> getDigitalWalletName(nullWalltet));

        DigitalWallet wallet = new DigitalWallet();
        assertNull(getDigitalWalletName(wallet));

        wallet.setPaymentService(new PaymentServiceRef(REF));
        assertEquals(REF, getDigitalWalletName(wallet));

        wallet.setPaymentService(null);
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertEquals(LegacyDigitalWalletProvider.rbkmoney.name(), getDigitalWalletName(wallet));

        wallet.setPaymentService(new PaymentServiceRef(REF));
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertEquals(REF, getDigitalWalletName(wallet));
    }

    @Test
    void getDigitalWalletNameTest() {
        assertNull(getDigitalWalletName(null, null));
        assertNull(getDigitalWalletName(new PaymentServiceRef(), null));
        assertNull(getDigitalWalletName(new PaymentServiceRef(EMPTY), null));
        assertEquals(
                REF,
                getDigitalWalletName(new PaymentServiceRef(REF), null)
        );
        assertEquals(
                REF,
                getDigitalWalletName(new PaymentServiceRef(REF), LegacyDigitalWalletProvider.rbkmoney)
        );
        assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                getDigitalWalletName(null, LegacyDigitalWalletProvider.rbkmoney)
        );
        assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                getDigitalWalletName(new PaymentServiceRef(), LegacyDigitalWalletProvider.rbkmoney)
        );
        assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                getDigitalWalletName(new PaymentServiceRef(EMPTY), LegacyDigitalWalletProvider.rbkmoney)
        );
    }
}
