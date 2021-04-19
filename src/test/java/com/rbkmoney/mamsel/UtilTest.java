package com.rbkmoney.mamsel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UtilTest {

    @Test
    void isEmptyTest() {
        assertTrue(Util.isEmpty(null));
        assertTrue(Util.isEmpty(""));
        assertFalse(Util.isEmpty("123"));
    }
}
