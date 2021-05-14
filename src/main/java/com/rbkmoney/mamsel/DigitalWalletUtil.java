package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.DigitalWallet;
import com.rbkmoney.damsel.domain.DigitalWalletConditionDefinition;
import com.rbkmoney.damsel.domain.LegacyDigitalWalletProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Utility class to help with former DigitalWalletProvider.<br>
 * It can have values in two fields: <br>
 * -> PaymentServiceRef <br>
 * -> LegacyDigitalWalletProvider
 */
public class DigitalWalletUtil {

    private DigitalWalletUtil() {
    }

    public static Optional<String> getDigitalWalletName(@NotNull DigitalWallet digitalWallet) {
        return getDigitalWalletName(digitalWallet.getPaymentService(), digitalWallet.getProviderDeprecated());
    }

    public static Optional<String> getDigitalWalletName(
            @NotNull DigitalWalletConditionDefinition digitalWalletConditionDefinition) {
        Optional<DigitalWalletConditionDefinition> definition = Optional.of(digitalWalletConditionDefinition);
        return definition
                .filter(DigitalWalletConditionDefinition::isSetPaymentServiceIs)
                .map(DigitalWalletConditionDefinition::getPaymentServiceIs)
                .map(PaymentServiceRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> definition
                        .filter(DigitalWalletConditionDefinition::isSetProviderIsDeprecated)
                        .map(DigitalWalletConditionDefinition::getProviderIsDeprecated)
                        .map(Enum::name));
    }

    public static Optional<String> getDigitalWalletName(
            PaymentServiceRef paymentServiceRef,
            LegacyDigitalWalletProvider legacyDigitalWalletProvider) {
        return Optional.ofNullable(paymentServiceRef)
                .map(PaymentServiceRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> Optional.ofNullable(legacyDigitalWalletProvider)
                        .map(Enum::name));
    }

    public static boolean isSetDigitalWallet(@NotNull DigitalWallet digitalWallet) {
        return digitalWallet.isSetPaymentService() || digitalWallet.isSetProviderDeprecated();
    }

    public static boolean isSetDigitalWallet(
            @NotNull DigitalWalletConditionDefinition digitalWalletConditionDefinition) {
        return digitalWalletConditionDefinition.isSetPaymentServiceIs()
                || digitalWalletConditionDefinition.isSetProviderIsDeprecated();
    }
}
