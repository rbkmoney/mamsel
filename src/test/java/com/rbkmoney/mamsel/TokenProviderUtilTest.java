package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.BankCardTokenServiceRef;
import com.rbkmoney.damsel.domain.LegacyBankCardTokenProvider;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.TokenizedBankCard;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.TokenProviderUtil.getTokenProviderName;
import static com.rbkmoney.mamsel.TokenProviderUtil.isSetTokenProvider;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TokenProviderUtilTest {

    @Test
    void getTokenProviderNameTest_BankCard() {
        BankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> TokenProviderUtil.getTokenProviderName(nullCard));

        BankCard card = new BankCard();
        assertTrue(TokenProviderUtil.getTokenProviderName(card).isEmpty());

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertEquals(REF, TokenProviderUtil.getTokenProviderName(card).get());

        card.setPaymentToken(null);
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                TokenProviderUtil.getTokenProviderName(card).get()
        );

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, TokenProviderUtil.getTokenProviderName(card).get());
    }

    @Test
    void getTokenProviderNameTest_PaymentSystemCondition() {
        PaymentSystemCondition nullCondition = null;
        assertThrows(NullPointerException.class, () -> TokenProviderUtil.getTokenProviderName(nullCondition));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertTrue(TokenProviderUtil.getTokenProviderName(condition).isEmpty());

        condition.setTokenServiceIs(new BankCardTokenServiceRef(REF));
        assertEquals(REF, TokenProviderUtil.getTokenProviderName(condition).get());

        condition.setTokenServiceIs(null);
        condition.setTokenProviderIsDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                TokenProviderUtil.getTokenProviderName(condition).get()
        );

        condition.setTokenServiceIs(new BankCardTokenServiceRef(REF));
        condition.setTokenProviderIsDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, TokenProviderUtil.getTokenProviderName(condition).get());
    }

    @Test
    void getTokenProviderNameTest() {
        assertTrue(getTokenProviderName(null, null).isEmpty());
        assertTrue(getTokenProviderName(new BankCardTokenServiceRef(), null).isEmpty());
        assertTrue(getTokenProviderName(new BankCardTokenServiceRef(EMPTY), null).isEmpty());
        assertEquals(
                REF,
                getTokenProviderName(new BankCardTokenServiceRef(REF), null).get()
        );
        assertEquals(
                REF,
                getTokenProviderName(new BankCardTokenServiceRef(REF), LegacyBankCardTokenProvider.yandexpay).get()
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(null, LegacyBankCardTokenProvider.yandexpay).get()
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(new BankCardTokenServiceRef(), LegacyBankCardTokenProvider.yandexpay).get()
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(new BankCardTokenServiceRef(EMPTY), LegacyBankCardTokenProvider.yandexpay).get()
        );
    }

    @Test
    void isSetTokenProviderTest_BankCard() {
        BankCard nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTokenProvider(nullObj));

        BankCard card = new BankCard();
        assertFalse(isSetTokenProvider(card));

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertTrue(isSetTokenProvider(card));

        card = new BankCard();
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertTrue(isSetTokenProvider(card));
    }

    @Test
    void isSetTokenProviderTest_PaymentSystemCondition() {
        PaymentSystemCondition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTokenProvider(nullObj));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertFalse(isSetTokenProvider(condition));

        condition.setTokenServiceIs(new BankCardTokenServiceRef(REF));
        assertTrue(isSetTokenProvider(condition));

        condition = new PaymentSystemCondition();
        condition.setTokenProviderIsDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertTrue(isSetTokenProvider(condition));
    }
}
