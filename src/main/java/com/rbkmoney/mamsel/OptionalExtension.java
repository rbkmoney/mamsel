package com.rbkmoney.mamsel;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public interface OptionalExtension {

    /**
     * If a value is present, returns an {@code Optional} produced by the main supplying function,
     * otherwise returns an {@code Optional} produced by the additional supplying function.
     * Added from @or@ method declaration from {@code Optional} class file
     * because @or@ method does not exist in JDK (8) used by CI
     *
     * @throws NullPointerException if the supplying function is {@code null} or
     *                              produces a {@code null} result
     */
    static <T> Optional<T> isPresentOr(
            Supplier<Optional<T>> supplierMain,
            Supplier<Optional<T>> supplierAdditional) {
        Optional<T> optionalMain = Objects.requireNonNull(supplierMain.get());
        return optionalMain.isPresent()
                ? optionalMain
                : Objects.requireNonNull(supplierAdditional.get());
    }
}
