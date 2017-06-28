package com.implemica.task3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Евгений on 27.06.2017.
 */
public class BigIntTest {
    @Test
    public void testTenFactorialDigitSum() {
        assertEquals(27, getNFactorialDigitSum(10));
    }

    @Test
    public void testOneHundredFactorialDigitSum() {
        assertEquals(648, getNFactorialDigitSum(100));
    }

    @Test
    public void testOneThousandFactorialDigitSum() {
        assertEquals(10539, getNFactorialDigitSum(1000));
    }

    private int getNFactorialDigitSum(int n) {
        BigInt integer = BigInt.valueOf(1);
        for (int i = 1; i <= n; i++) {
            integer = integer.karatsubaMultiply(BigInt.valueOf(i));
        }
        String result = integer.toString();
        int sum = 0;
        for (char digit : result.toCharArray()) {
            sum += Integer.valueOf(String.valueOf(digit));
        }
        return sum;
    }
}
