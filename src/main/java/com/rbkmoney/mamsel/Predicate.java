package com.rbkmoney.mamsel;

import java.util.Objects;

public interface Predicate {

    /**
     * ci компилит библиотеки на 8 джаве
     * */
    static <T> java.util.function.Predicate<T> not(java.util.function.Predicate<? super T> target) {
        Objects.requireNonNull(target);
        return (java.util.function.Predicate<T>)target.negate();
    }
}
