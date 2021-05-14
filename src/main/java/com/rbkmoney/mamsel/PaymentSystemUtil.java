package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import com.rbkmoney.damsel.payment_tool_provider.CardInfo;
import com.rbkmoney.mamsel.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Utility class to help with former BankCardPaymentSystem.<br>
 * It can have values in two fields: <br>
 * -> PaymentSystemRef <br>
 * -> LegacyBankCardPaymentSystem
 */
public class PaymentSystemUtil {

    private PaymentSystemUtil() {
    }

    public static String getPaymentSystemName(@NotNull BankCard bankCard) {
        return getPaymentSystemName(bankCard.getPaymentSystem(), bankCard.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(@NotNull CardInfo cardInfo) {
        return getPaymentSystemName(cardInfo.getPaymentSystem(), cardInfo.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(
            PaymentSystemRef paymentSystemRef,
            LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        return getPaymentSystemNameIfPresent(paymentSystemRef, legacyBankCardPaymentSystem)
                .orElse(null);
    }

    public static Optional<String> getPaymentSystemNameIfPresent(
            PaymentSystemRef paymentSystemRef,
            LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        return Optional.ofNullable(paymentSystemRef)
                .map(PaymentSystemRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> Optional.ofNullable(legacyBankCardPaymentSystem)
                        .map(Enum::name));
    }

    public static boolean isSetPaymentSystem(@NotNull BankCard bankCard) {
        return bankCard.isSetPaymentSystem() || bankCard.isSetPaymentSystemDeprecated();
    }

    public static boolean isSetPaymentSystem(@NotNull CardInfo cardInfo) {
        return cardInfo.isSetPaymentSystem() || cardInfo.isSetPaymentSystemDeprecated();
    }
}
