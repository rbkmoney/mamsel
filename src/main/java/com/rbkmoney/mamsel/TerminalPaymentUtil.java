package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyTerminalPaymentProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentTerminal;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Utility class to help with former TerminalPaymentProvider.<br>
 * It can have values in two fields: <br>
 * -> PaymentServiceRef <br>
 * -> LegacyTerminalPaymentProvider
 */
public class TerminalPaymentUtil {

    private TerminalPaymentUtil() {
    }

    public static String getTerminalPaymentProviderName(@NotNull PaymentTerminal paymentTerminal) {
        return getTerminalPaymentProviderName(
                paymentTerminal.getPaymentService(),
                paymentTerminal.getTerminalTypeDeprecated());
    }

    public static String getTerminalPaymentProviderName(
            PaymentServiceRef paymentServiceRef,
            LegacyTerminalPaymentProvider legacyTerminalPaymentProvider) {
        return getTerminalPaymentProviderNameIfPresent(paymentServiceRef, legacyTerminalPaymentProvider)
                .orElse(null);
    }

    public static Optional<String> getTerminalPaymentProviderNameIfPresent(
            PaymentServiceRef paymentServiceRef,
            LegacyTerminalPaymentProvider legacyTerminalPaymentProvider) {
        return OptionalExtension.isPresentOr(
                () -> Optional.ofNullable(paymentServiceRef)
                        .map(PaymentServiceRef::getId)
                        .filter(Predicate.not(StringUtils::isEmpty)),
                () -> Optional.ofNullable(legacyTerminalPaymentProvider)
                        .map(Enum::name)
        );
    }
}
