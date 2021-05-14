package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Utility class to help with former CryptoCurrency.<br>
 * It can have values in two fields: <br>
 * -> CryptoCurrencyRef <br>
 * -> LegacyCryptoCurrency
 */
public class CryptoCurrencyUtil {

    private CryptoCurrencyUtil() {
    }

    public static String getCryptoCurrencyName(@NotNull PaymentTool paymentTool) {
        return getCryptoCurrencyNameIfPresent(paymentTool)
                .orElse(null);
    }

    public static String getCryptoCurrencyName(@NotNull CryptoWallet cryptoWallet) {
        return getCryptoCurrencyName(cryptoWallet.getCryptoCurrency(), cryptoWallet.getCryptoCurrencyDeprecated());
    }

    public static String getCryptoCurrencyName(
            CryptoCurrencyRef cryptoCurrencyRef,
            LegacyCryptoCurrency legacyCryptoCurrency) {
        return getCryptoCurrencyNameIfPresent(cryptoCurrencyRef, legacyCryptoCurrency)
                .orElse(null);
    }

    public static Optional<String> getCryptoCurrencyNameIfPresent(@NotNull PaymentTool paymentTool) {
        Optional<PaymentTool> tool = Optional.of(paymentTool);
        return tool
                .filter(PaymentTool::isSetCryptoCurrency)
                .map(PaymentTool::getCryptoCurrency)
                .map(CryptoCurrencyRef::getId)
                .filter(Predicate.not(StringUtils::isEmpty))
                .or(() -> tool
                        .filter(PaymentTool::isSetCryptoCurrencyDeprecated)
                        .map(PaymentTool::getCryptoCurrencyDeprecated)
                        .map(Enum::name));
    }

    public static Optional<String> getCryptoCurrencyNameIfPresent(
            CryptoCurrencyRef cryptoCurrencyRef,
            LegacyCryptoCurrency legacyCryptoCurrency) {
        return Optional.ofNullable(cryptoCurrencyRef)
                .map(CryptoCurrencyRef::getId)
                .filter(Predicate.not(StringUtils::isEmpty))
                .or(() -> Optional.ofNullable(legacyCryptoCurrency)
                        .map(Enum::name));
    }

    public static boolean isSetCryptoCurrency(@NotNull CryptoWallet cryptoWallet) {
        return cryptoWallet.isSetCryptoCurrency() || cryptoWallet.isSetCryptoCurrencyDeprecated();
    }

    public static boolean isSetCryptoCurrency(@NotNull PaymentTool paymentTool) {
        return paymentTool.isSetCryptoCurrency() || paymentTool.isSetCryptoCurrencyDeprecated();
    }
}
