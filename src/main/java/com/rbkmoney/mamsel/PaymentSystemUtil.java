package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import com.rbkmoney.damsel.payment_tool_provider.CardInfo;

import javax.validation.constraints.NotNull;
import java.util.Optional;

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
        return OptionalExtension.isPresentOr(
                () -> Optional.ofNullable(paymentSystemRef)
                        .map(PaymentSystemRef::getId)
                        .filter(Predicate.not(StringUtils::isEmpty)),
                () -> Optional.ofNullable(legacyBankCardPaymentSystem)
                        .map(Enum::name)
        );
    }
}
