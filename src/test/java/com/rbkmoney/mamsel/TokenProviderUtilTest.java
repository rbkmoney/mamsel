package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardTokenServiceRef;
import com.rbkmoney.damsel.domain.LegacyBankCardTokenProvider;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.TokenProviderUtil.getTokenProviderName;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class TokenProviderUtilTest {

    @Test
    void getTokenProviderNameTest_BankCard() {
        BankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> TokenProviderUtil.getTokenProviderName(nullCard));

        BankCard card = new BankCard();
        assertNull(TokenProviderUtil.getTokenProviderName(card));

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertEquals(REF, TokenProviderUtil.getTokenProviderName(card));

        card.setPaymentToken(null);
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                TokenProviderUtil.getTokenProviderName(card)
        );

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, TokenProviderUtil.getTokenProviderName(card));
    }

    @Test
    void getTokenProviderNameTest() {
        assertNull(getTokenProviderName(null, null));
        assertNull(getTokenProviderName(new BankCardTokenServiceRef(), null));
        assertNull(getTokenProviderName(new BankCardTokenServiceRef(EMPTY), null));
        assertEquals(
                REF,
                getTokenProviderName(new BankCardTokenServiceRef(REF), null)
        );
        assertEquals(
                REF,
                getTokenProviderName(new BankCardTokenServiceRef(REF), LegacyBankCardTokenProvider.yandexpay)
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(null, LegacyBankCardTokenProvider.yandexpay)
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(new BankCardTokenServiceRef(), LegacyBankCardTokenProvider.yandexpay)
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(new BankCardTokenServiceRef(EMPTY), LegacyBankCardTokenProvider.yandexpay)
        );
    }
}
