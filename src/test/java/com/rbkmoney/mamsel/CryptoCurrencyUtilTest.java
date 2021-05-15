package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.CryptoCurrencyRef;
import com.rbkmoney.damsel.domain.CryptoWallet;
import com.rbkmoney.damsel.domain.LegacyCryptoCurrency;
import com.rbkmoney.damsel.domain.PaymentTool;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.CryptoCurrencyUtil.getCryptoCurrencyName;
import static com.rbkmoney.mamsel.CryptoCurrencyUtil.isSetCryptoCurrency;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class CryptoCurrencyUtilTest {

    @Test
    void getCryptoCurrencyNameTest_PaymentTool() {
        PaymentTool nullMethod = null;
        assertThrows(NullPointerException.class, () -> getCryptoCurrencyName(nullMethod));

        PaymentTool tool = new PaymentTool();
        assertNull(getCryptoCurrencyName(tool));

        tool.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, getCryptoCurrencyName(tool));

        tool.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(LegacyCryptoCurrency.bitcoin.name(), getCryptoCurrencyName(tool));
    }

    @Test
    void getCryptoCurrencyNameTest_CryptoWallet() {
        CryptoWallet nullWallet = null;
        assertThrows(NullPointerException.class, () -> getCryptoCurrencyName(nullWallet));

        CryptoWallet commerce = new CryptoWallet();
        assertNull(getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(null);
        commerce.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(LegacyCryptoCurrency.bitcoin.name(), getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(new CryptoCurrencyRef(REF));
        commerce.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(REF, getCryptoCurrencyName(commerce));
    }

    @Test
    void getCryptoCurrencyNameTest() {
        assertNull(getCryptoCurrencyName(null, null));
        assertNull(getCryptoCurrencyName(new CryptoCurrencyRef(), null));
        assertNull(getCryptoCurrencyName(new CryptoCurrencyRef(EMPTY), null));
        assertEquals(
                REF,
                getCryptoCurrencyName(new CryptoCurrencyRef(REF), null)
        );
        assertEquals(
                REF,
                getCryptoCurrencyName(new CryptoCurrencyRef(REF), LegacyCryptoCurrency.bitcoin)
        );
        assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                getCryptoCurrencyName(null, LegacyCryptoCurrency.bitcoin)
        );
        assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                getCryptoCurrencyName(new CryptoCurrencyRef(), LegacyCryptoCurrency.bitcoin)
        );
        assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                getCryptoCurrencyName(new CryptoCurrencyRef(EMPTY), LegacyCryptoCurrency.bitcoin)
        );
    }

    @Test
    void isSetCryptoCurrencyTest_PaymentTool() {
        PaymentTool nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCryptoCurrency(nullObj));

        PaymentTool tool = new PaymentTool();
        assertFalse(isSetCryptoCurrency(tool));

        tool.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertTrue(isSetCryptoCurrency(tool));

        tool = new PaymentTool();
        tool.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertTrue(isSetCryptoCurrency(tool));
    }
}
