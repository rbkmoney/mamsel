package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyTerminalPaymentProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentTerminal;
import com.rbkmoney.damsel.domain.PaymentTerminalConditionDefinition;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Utility class to help with former TerminalPaymentProvider.<br>
 * It can have values in two fields: <br>
 * -> PaymentServiceRef <br>
 * -> LegacyTerminalPaymentProvider
 */
public class TerminalPaymentUtil {

    private TerminalPaymentUtil() {
    }

    public static Optional<String> getTerminalPaymentProviderName(@NotNull PaymentTerminal paymentTerminal) {
        return getTerminalPaymentProviderName(
                paymentTerminal.getPaymentService(),
                paymentTerminal.getTerminalTypeDeprecated());
    }

    public static Optional<String> getTerminalPaymentProviderName(
            @NotNull PaymentTerminalConditionDefinition paymentTerminalConditionDefinition) {
        Optional<PaymentTerminalConditionDefinition> definition = Optional.of(paymentTerminalConditionDefinition);
        return definition
                .filter(PaymentTerminalConditionDefinition::isSetPaymentServiceIs)
                .map(PaymentTerminalConditionDefinition::getPaymentServiceIs)
                .map(PaymentServiceRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> definition
                        .filter(PaymentTerminalConditionDefinition::isSetProviderIsDeprecated)
                        .map(PaymentTerminalConditionDefinition::getProviderIsDeprecated)
                        .map(Enum::name));
    }

    public static Optional<String> getTerminalPaymentProviderName(
            PaymentServiceRef paymentServiceRef,
            LegacyTerminalPaymentProvider legacyTerminalPaymentProvider) {
        return Optional.ofNullable(paymentServiceRef)
                .map(PaymentServiceRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> Optional.ofNullable(legacyTerminalPaymentProvider)
                        .map(Enum::name));
    }

    public static boolean isSetTerminalPaymentProvider(@NotNull PaymentTerminal paymentTerminal) {
        return paymentTerminal.isSetPaymentService() || paymentTerminal.isSetTerminalTypeDeprecated();
    }

    public static boolean isSetTerminalPaymentProvider(
            @NotNull PaymentTerminalConditionDefinition paymentTerminalConditionDefinition) {
        return paymentTerminalConditionDefinition.isSetPaymentServiceIs()
                || paymentTerminalConditionDefinition.isSetProviderIsDeprecated();
    }
}
