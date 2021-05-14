package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.DigitalWallet;
import com.rbkmoney.damsel.domain.LegacyDigitalWalletProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Utility class to help with former DigitalWalletProvider.<br>
 * It can have values in two fields: <br>
 * -> PaymentServiceRef <br>
 * -> LegacyDigitalWalletProvider
 */
public class DigitalWalletUtil {

    private DigitalWalletUtil() {
    }

    public static String getDigitalWalletName(@NotNull DigitalWallet digitalWallet) {
        return getDigitalWalletName(digitalWallet.getPaymentService(), digitalWallet.getProviderDeprecated());
    }

    public static String getDigitalWalletName(
            PaymentServiceRef paymentServiceRef,
            LegacyDigitalWalletProvider legacyDigitalWalletProvider) {
        return getDigitalWalletNameIfPresent(paymentServiceRef, legacyDigitalWalletProvider)
                .orElse(null);
    }

    public static Optional<String> getDigitalWalletNameIfPresent(
            PaymentServiceRef paymentServiceRef,
            LegacyDigitalWalletProvider legacyDigitalWalletProvider) {
        return OptionalExtension.isPresentOr(
                () -> Optional.ofNullable(paymentServiceRef)
                        .map(PaymentServiceRef::getId)
                        .filter(Predicate.not(StringUtils::isEmpty)),
                () -> Optional.ofNullable(legacyDigitalWalletProvider)
                        .map(Enum::name)
        );
    }
}
