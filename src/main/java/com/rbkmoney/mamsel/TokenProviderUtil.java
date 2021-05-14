package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardTokenServiceRef;
import com.rbkmoney.damsel.domain.LegacyBankCardTokenProvider;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Utility class to help with former BankCardTokenProvider.<br>
 * It can have values in two fields: <br>
 * -> BankCardTokenServiceRef <br>
 * -> LegacyBankCardTokenProvider
 */
public class TokenProviderUtil {

    private TokenProviderUtil() {
    }

    public static Optional<String> getTokenProviderName(@NotNull BankCard bankCard) {
        return getTokenProviderName(bankCard.getPaymentToken(), bankCard.getTokenProviderDeprecated());
    }

    public static Optional<String> getTokenProviderName(@NotNull PaymentSystemCondition paymentSystemCondition) {
        return getTokenProviderName(
                paymentSystemCondition.getTokenServiceIs(),
                paymentSystemCondition.getTokenProviderIsDeprecated());
    }

    public static Optional<String> getTokenProviderName(
            BankCardTokenServiceRef bankCardTokenServiceRef,
            LegacyBankCardTokenProvider legacyBankCardTokenProvider) {
        return Optional.ofNullable(bankCardTokenServiceRef)
                .map(BankCardTokenServiceRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> Optional.ofNullable(legacyBankCardTokenProvider)
                        .map(Enum::name));
    }

    public static boolean isSetTokenProvider(@NotNull BankCard bankCard) {
        return bankCard.isSetPaymentToken() || bankCard.isSetTokenProviderDeprecated();
    }

    public static boolean isSetTokenProvider(@NotNull PaymentSystemCondition paymentSystemCondition) {
        return paymentSystemCondition.isSetTokenServiceIs() || paymentSystemCondition.isSetTokenProviderIsDeprecated();
    }
}
