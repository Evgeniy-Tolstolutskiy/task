package com.implemica.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Евгений on 25.06.2017.
 * <p>
 * Class for storin large numbers. All methods in this class are <b>not</b> supporting negative numbers.;
 */
public class BigInt {
    private static final int INTEGER_NUMBER_MAX_LENGTH = 10;
    private int[] digits;

    private BigInt(int[] digits) {
        this.digits = digits;
    }

    public static BigInt valueOf(long value) {
        if (value == 0) {
            return new BigInt(new int[]{0});
        }

        List<Integer> digitList = new ArrayList<>();
        while (value > 0) {
            digitList.add((int) (value % 10));
            value /= 10;
        }
        return new BigInt(digitList.stream().mapToInt(i -> i).toArray());
    }

    /**
     * Does multiply one {@link BigInt} object by another with Karatsuba algorithm.
     *
     * @param other
     * @return
     */
    public BigInt karatsubaMultiply(BigInt other) {
        if (canProduceLongResult(this, other, false)) {
            return BigInt.valueOf(this.getLong() * other.getLong());
        }
        int maxLength = Math.max(this.getLength(), other.getLength());
        maxLength = (maxLength / 2) + (maxLength % 2);

        BigInt divider = powOfTen(maxLength);

        BigInt high1 = this.divideByPowerOfTen(divider);
        BigInt low1 = this.subtract(high1.multiplyByPowerOfTen(divider));
        BigInt high2 = other.divideByPowerOfTen(divider);
        BigInt low2 = other.subtract(high2.multiplyByPowerOfTen(divider));

        BigInt part0 = low1.karatsubaMultiply(low2);
        BigInt part1 = low1.add(high1).karatsubaMultiply(low2.add(high2));
        BigInt part2 = high1.karatsubaMultiply(high2);

        BigInt first = part2.multiplyByPowerOfTen(powOfTen(maxLength * 2));
        BigInt second = (part1.subtract(part0).subtract(part2)).multiplyByPowerOfTen(divider);
        return first.add(second).add(part0);
    }

    /**
     * Does 10^{@code exponent} operation.
     *
     * @param exponent
     * @return
     */
    private BigInt powOfTen(int exponent) {
        int resultLength = exponent + 1;
        int[] result = new int[resultLength];
        result[resultLength - 1] = 1;
        return new BigInt(result);
    }

    /**
     * Does {@code other}/10^n operation.
     *
     * @param other
     * @return
     */
    private BigInt divideByPowerOfTen(BigInt other) {
        if (canProduceLongResult(this, other, true)) {
            return BigInt.valueOf(this.getLong() / other.getLong());
        }

        if (this.getLength() < other.getLength()) {
            return BigInt.valueOf(0);
        }

        return new BigInt(Arrays.copyOfRange(this.digits, other.digits.length - 1, this.digits.length));
    }

    /**
     * Does {@code other}*10^n operation.
     *
     * @param other
     * @return
     */
    private BigInt multiplyByPowerOfTen(BigInt other) {
        if (canProduceLongResult(this, other, false)) {
            return BigInt.valueOf(this.getLong() * other.getLong());
        }

        if (this.equals(BigInt.valueOf(0))) {
            return BigInt.valueOf(0);
        }

        int[] result = new int[this.digits.length + other.digits.length - 1];
        for (int i = 0; i < other.digits.length - 1; i++) {
            result[i] = 0;
        }
        for (int i = other.digits.length - 1; i < result.length; i++) {
            result[i] = this.digits[i - (result.length - this.digits.length)];
        }
        return new BigInt(result);
    }

    /**
     * Does long addition of two {@link BigInt} objects.
     *
     * @param other
     * @return
     */
    private BigInt add(BigInt other) {
        if (canProduceLongResult(this, other, true)) {
            return BigInt.valueOf(this.getLong() + other.getLong());
        }

        BigInt maxLengthBigInt;
        BigInt minLengthBigInt;
        if (this.getLength() > other.getLength()) {
            maxLengthBigInt = this;
            minLengthBigInt = other;
        } else {
            maxLengthBigInt = other;
            minLengthBigInt = this;
        }

        int[] otherDigitsCompletedByZeros = new int[maxLengthBigInt.digits.length];
        for (int i = 0; i < minLengthBigInt.getLength(); i++) {
            otherDigitsCompletedByZeros[i] = minLengthBigInt.digits[i];
        }
        for (int i = minLengthBigInt.getLength(); i < otherDigitsCompletedByZeros.length; i++) {
            otherDigitsCompletedByZeros[i] = 0;
        }

        List<Integer> result = new ArrayList<>();

        int shiftedOne = 0;
        for (int i = 0; i < maxLengthBigInt.digits.length; i++) {
            int sum = maxLengthBigInt.digits[i] + otherDigitsCompletedByZeros[i] + shiftedOne;
            if (sum > 9) {
                result.add(sum % 10);
                shiftedOne = 1;
            } else {
                result.add(sum);
                shiftedOne = 0;
            }
        }
        if (shiftedOne == 1) {
            result.add(1);
        }

        return new BigInt(result.stream().mapToInt(i -> i).toArray());
    }

    /**
     * Does long subtraction of two {@link BigInt} objects. This method can produce correct result <b>only</b> if minuend is <b>less</b> than {@code other} - subtrahend.
     *
     * @param other
     * @return
     */
    private BigInt subtract(BigInt other) {
        if (canProduceLongResult(this, other, true)) {
            return BigInt.valueOf(this.getLong() - other.getLong());
        }

        int[] otherDigitsCompletedByZeros = new int[this.digits.length];
        for (int i = 0; i < other.getLength(); i++) {
            otherDigitsCompletedByZeros[i] = other.digits[i];
        }
        for (int i = other.getLength(); i < otherDigitsCompletedByZeros.length; i++) {
            otherDigitsCompletedByZeros[i] = 0;
        }

        List<Integer> result = new ArrayList<>();

        int shiftedOne = 0;
        for (int i = 0; i < this.digits.length; i++) {
            int subtracted = this.digits[i] - otherDigitsCompletedByZeros[i] - shiftedOne;
            if (subtracted < 0) {
                result.add((subtracted + 10) % 10);
                shiftedOne = 1;
            } else {
                result.add(subtracted);
                shiftedOne = 0;
            }
        }

        removeFrontZeros(result);

        return new BigInt(result.stream().mapToInt(i -> i).toArray());
    }

    private void removeFrontZeros(List<Integer> subtractionResult) {
        for (int i = subtractionResult.size() - 1; i >= 0; i--) {
            if (subtractionResult.get(i) == 0) {
                subtractionResult.remove(i);
            } else {
                break;
            }
        }
        if (subtractionResult.isEmpty()) {
            subtractionResult.add(0);
        }
    }

    private int getLength() {
        return digits.length;
    }

    /**
     * Returns {@code long} number based on class data.
     *
     * @return {@code long} number
     */
    private long getLong() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            stringBuilder.append(digits[i]);
        }

        return Long.valueOf(stringBuilder.toString());
    }

    private boolean canProduceLongResult(BigInt firstOperand, BigInt secondOperand, boolean strict) {
        if (strict) {
            return firstOperand.getLength() <= INTEGER_NUMBER_MAX_LENGTH && secondOperand.getLength() <= INTEGER_NUMBER_MAX_LENGTH;
        }
        return firstOperand.getLength() < INTEGER_NUMBER_MAX_LENGTH && secondOperand.getLength() < INTEGER_NUMBER_MAX_LENGTH;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            stringBuilder.append(digits[i]);
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigInt bigInt = (BigInt) o;

        return Arrays.equals(digits, bigInt.digits);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(digits);
    }
}
