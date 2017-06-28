package com.implemica.task3;

/**
 * Created by Евгений on 24.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        BigInt integer = BigInt.valueOf(1);
        for (int i = 1; i <= 1000; i++) {
            integer = integer.karatsubaMultiply(BigInt.valueOf(i));
        }
        String result = integer.toString();
        int sum = 0;
        for (char digit : result.toCharArray()) {
            sum += Integer.valueOf(String.valueOf(digit));
        }
        System.out.println(sum);
    }
}
