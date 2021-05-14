package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyMobileOperator;
import com.rbkmoney.damsel.domain.MobileCommerce;
import com.rbkmoney.damsel.domain.MobileCommerceConditionDefinition;
import com.rbkmoney.damsel.domain.MobileOperatorRef;
import org.apache.commons.lang3.StringUtils;

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

    public static Optional<String> getMobileOperatorName(@NotNull MobileCommerce mobileCommerce) {
        return getMobileOperatorName(mobileCommerce.getOperator(), mobileCommerce.getOperatorDeprecated());
    }

    public static Optional<String> getMobileOperatorName(
            @NotNull MobileCommerceConditionDefinition mobileCommerceConditionDefinition) {
        Optional<MobileCommerceConditionDefinition> definition = Optional.of(mobileCommerceConditionDefinition);
        return definition
                .filter(MobileCommerceConditionDefinition::isSetOperatorIs)
                .map(MobileCommerceConditionDefinition::getOperatorIs)
                .map(MobileOperatorRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> definition
                        .filter(MobileCommerceConditionDefinition::isSetOperatorIsDeprecated)
                        .map(MobileCommerceConditionDefinition::getOperatorIsDeprecated)
                        .map(Enum::name));
    }

    public static Optional<String> getMobileOperatorName(
            MobileOperatorRef mobileOperatorRef,
            LegacyMobileOperator legacyMobileOperator) {
        return Optional.ofNullable(mobileOperatorRef)
                .map(MobileOperatorRef::getId)
                .filter(Predicate.not(StringUtils::isBlank))
                .or(() -> Optional.ofNullable(legacyMobileOperator)
                        .map(Enum::name));
    }

    public static boolean isSetMobileOperatorName(@NotNull MobileCommerce mobileCommerce) {
        return mobileCommerce.isSetOperator() || mobileCommerce.isSetOperatorDeprecated();
    }

    public static boolean isSetMobileOperatorName(
            @NotNull MobileCommerceConditionDefinition mobileCommerceConditionDefinition) {
        return mobileCommerceConditionDefinition.isSetOperatorIs()
                || mobileCommerceConditionDefinition.isSetOperatorIsDeprecated();
    }
}
