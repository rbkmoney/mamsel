package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.*;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Utility class to help with former CryptoCurrency.<br>
 * It can have values in two fields: <br>
 * -> CryptoCurrencyRef <br>
 * -> LegacyCryptoCurrency
 */
public class CryptoCurrencyUtil {

    private CryptoCurrencyUtil() {
    }

    public static Optional<String> getCryptoCurrencyName(@NotNull CryptoWallet cryptoWallet) {
        return getCryptoCurrencyName(cryptoWallet.getCryptoCurrency(), cryptoWallet.getCryptoCurrencyDeprecated());
    }

    public static Optional<String> getCryptoCurrencyName(@NotNull PaymentTool paymentTool) {
        Optional<PaymentTool> tool = Optional.of(paymentTool);
        return tool
                .filter(PaymentTool::isSetCryptoCurrency)
                .map(PaymentTool::getCryptoCurrency)
                .map(CryptoCurrencyRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> tool
                        .filter(PaymentTool::isSetCryptoCurrencyDeprecated)
                        .map(PaymentTool::getCryptoCurrencyDeprecated)
                        .map(Enum::name));
    }

    public static Optional<String> getCryptoCurrencyName(
            @NotNull CryptoCurrencyConditionDefinition cryptoCurrencyConditionDefinition) {
        Optional<CryptoCurrencyConditionDefinition> definition = Optional.of(cryptoCurrencyConditionDefinition);
        return definition
                .filter(CryptoCurrencyConditionDefinition::isSetCryptoCurrencyIs)
                .map(CryptoCurrencyConditionDefinition::getCryptoCurrencyIs)
                .map(CryptoCurrencyRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> definition
                        .filter(CryptoCurrencyConditionDefinition::isSetCryptoCurrencyIsDeprecated)
                        .map(CryptoCurrencyConditionDefinition::getCryptoCurrencyIsDeprecated)
                        .map(Enum::name));
    }

    public static Optional<String> getCryptoCurrencyName(
            CryptoCurrencyRef cryptoCurrencyRef,
            LegacyCryptoCurrency legacyCryptoCurrency) {
        return Optional.ofNullable(cryptoCurrencyRef)
                .map(CryptoCurrencyRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> Optional.ofNullable(legacyCryptoCurrency)
                        .map(Enum::name));
    }

    public static boolean isSetCryptoCurrency(@NotNull CryptoWallet cryptoWallet) {
        return cryptoWallet.isSetCryptoCurrency() || cryptoWallet.isSetCryptoCurrencyDeprecated();
    }

    public static boolean isSetCryptoCurrency(@NotNull PaymentTool paymentTool) {
        return paymentTool.isSetCryptoCurrency() || paymentTool.isSetCryptoCurrencyDeprecated();
    }

    public static boolean isSetCryptoCurrency(
            @NotNull CryptoCurrencyConditionDefinition cryptoCurrencyConditionDefinition) {
        return cryptoCurrencyConditionDefinition.isSetCryptoCurrencyIs()
                || cryptoCurrencyConditionDefinition.isSetCryptoCurrencyIsDeprecated();
    }
}
