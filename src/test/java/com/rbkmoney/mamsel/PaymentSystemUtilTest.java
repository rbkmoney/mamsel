package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import com.rbkmoney.damsel.domain.TokenizedBankCard;
import com.rbkmoney.damsel.payment_tool_provider.CardInfo;
import org.junit.jupiter.api.Test;


import static com.rbkmoney.mamsel.PaymentSystemUtil.getPaymentSystemName;
import static com.rbkmoney.mamsel.PaymentSystemUtil.isSetPaymentSystem;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class PaymentSystemUtilTest {

    @Test
    void getPaymentSystemNameTest_BankCard() {
        BankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullCard));

        BankCard card = new BankCard();
        assertNull(getPaymentSystemName(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(card));

        card.setPaymentSystem(null);
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(card));
    }

    @Test
    void getPaymentSystemNameTest_CardInfo() {
        CardInfo nullInfo = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullInfo));

        CardInfo cardInfo = new CardInfo();
        assertNull(getPaymentSystemName(cardInfo));

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(cardInfo));

        cardInfo.setPaymentSystem(null);
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(cardInfo));

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(cardInfo));
    }

    @Test
    void getPaymentSystemNameTest() {
        assertNull(getPaymentSystemName(null, null));
        assertNull(getPaymentSystemName(new PaymentSystemRef(), null));
        assertNull(getPaymentSystemName(new PaymentSystemRef(EMPTY), null));
        assertEquals(
                REF,
                getPaymentSystemName(new PaymentSystemRef(REF), null)
        );
        assertEquals(
                REF,
                getPaymentSystemName(new PaymentSystemRef(REF), LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(null, LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(new PaymentSystemRef(), LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(new PaymentSystemRef(EMPTY), LegacyBankCardPaymentSystem.ebt)
        );
    }

    @Test
    void isPaymentSystemSetTest_BankCard() {
        BankCard nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        BankCard card = new BankCard();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(card));

        card = new BankCard();
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(card));
    }

    @Test
    void isPaymentSystemSetTest_CardInfo() {
        CardInfo nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        CardInfo cardInfo = new CardInfo();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(cardInfo));

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(cardInfo));

        cardInfo = new CardInfo();
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(cardInfo));
    }
}
