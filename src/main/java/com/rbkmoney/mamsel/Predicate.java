package com.rbkmoney.mamsel;

import java.util.Objects;

public interface Predicate {

    /**
     * Returns a predicate that is the negation of the supplied predicate.
     * This is accomplished by returning result of the calling
     * {@code target.negate()}.
     * Added from @not@ method declaration from {@code Predicate} class file
     * because @not@ method does not exist in JDK (8) used by CI
     *
     * @param <T>    the type of arguments to the specified predicate
     * @param target predicate to negate
     * @return a predicate that negates the results of the supplied predicate
     * @throws NullPointerException if target is null
     * */
    static <T> java.util.function.Predicate<T> not(java.util.function.Predicate<T> target) {
        return Objects.requireNonNull(target).negate();
    }
}
