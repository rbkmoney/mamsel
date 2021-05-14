package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyMobileOperator;
import com.rbkmoney.damsel.domain.MobileCommerce;
import com.rbkmoney.damsel.domain.MobileOperatorRef;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Utility class to help with former MobileOperator.<br>
 * It can have values in two fields: <br>
 * -> MobileOperatorRef <br>
 * -> LegacyMobileOperator
 */
public class MobileOperatorUtil {

    private MobileOperatorUtil() {
    }

    public static String getMobileOperatorName(@NotNull MobileCommerce mobileCommerce) {
        return getMobileOperatorName(mobileCommerce.getOperator(), mobileCommerce.getOperatorDeprecated());
    }

    public static String getMobileOperatorName(
            MobileOperatorRef mobileOperatorRef,
            LegacyMobileOperator legacyMobileOperator) {
        return getMobileOperatorNameIfPresent(mobileOperatorRef, legacyMobileOperator)
                .orElse(null);
    }

    public static Optional<String> getMobileOperatorNameIfPresent(
            MobileOperatorRef mobileOperatorRef,
            LegacyMobileOperator legacyMobileOperator) {
        return Optional.ofNullable(mobileOperatorRef)
                .map(MobileOperatorRef::getId)
                .filter(Predicate.not(StringUtils::isEmpty))
                .or(() -> Optional.ofNullable(legacyMobileOperator)
                        .map(Enum::name));
    }

    public static boolean isSetMobileOperatorName(@NotNull MobileCommerce mobileCommerce) {
        return mobileCommerce.isSetOperator() || mobileCommerce.isSetOperatorDeprecated();
    }
}
