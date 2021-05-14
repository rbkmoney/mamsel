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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PaymentSystemUtilTest {

    @Test
    void getPaymentSystemNameTest_BankCard() {
        BankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullCard));

        BankCard card = new BankCard();
        assertTrue(getPaymentSystemName(card).isEmpty());

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(card).get());

        card.setPaymentSystem(null);
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(card).get());

        card.setPaymentSystem(new PaymentSystemRef(REF));
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(card).get());
    }

    @Test
    void getPaymentSystemNameTest_CardInfo() {
        CardInfo nullInfo = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullInfo));

        CardInfo cardInfo = new CardInfo();
        assertTrue(getPaymentSystemName(cardInfo).isEmpty());

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(cardInfo).get());

        cardInfo.setPaymentSystem(null);
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(cardInfo).get());

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(cardInfo).get());
    }

    @Test
    void getPaymentSystemNameTest_PaymentSystemCondition() {
        PaymentSystemCondition nullCondition = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullCondition));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertTrue(getPaymentSystemName(condition).isEmpty());

        condition.setPaymentSystemIs(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(condition).get());

        condition.setPaymentSystemIs(null);
        condition.setPaymentSystemIsDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(condition).get());

        condition.setPaymentSystemIs(new PaymentSystemRef(REF));
        condition.setPaymentSystemIsDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(condition).get());
    }

    @Test
    void getPaymentSystemNameTest() {
        assertTrue(getPaymentSystemName(null, null).isEmpty());
        assertTrue(getPaymentSystemName(new PaymentSystemRef(), null).isEmpty());
        assertTrue(getPaymentSystemName(new PaymentSystemRef(EMPTY), null).isEmpty());
        assertEquals(
                REF,
                getPaymentSystemName(new PaymentSystemRef(REF), null).get()
        );
        assertEquals(
                REF,
                getPaymentSystemName(new PaymentSystemRef(REF), LegacyBankCardPaymentSystem.ebt).get()
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(null, LegacyBankCardPaymentSystem.ebt).get()
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(new PaymentSystemRef(), LegacyBankCardPaymentSystem.ebt).get()
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(new PaymentSystemRef(EMPTY), LegacyBankCardPaymentSystem.ebt).get()
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

    @Test
    void isPaymentSystemSetTest_PaymentSystemCondition() {
        PaymentSystemCondition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetPaymentSystem(nullObj));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertFalse(isSetPaymentSystem(condition));

        condition.setPaymentSystemIs(new PaymentSystemRef(REF));
        assertTrue(isSetPaymentSystem(condition));

        condition = new PaymentSystemCondition();
        condition.setPaymentSystemIsDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(isSetPaymentSystem(condition));
    }
}
