package com.implemica.task1;

import com.implemica.task1.exception.NegativeNumberException;

/**
 * Created by Евгений on 22.06.2017.
 * This class provides functionality for counting all correct variants of bracket expressions.
 */
public class CorrectBracketVariantsCounter {

    /**
     * Counts all correct variants of bracket expressions with {@code bracketsCount} opening and {@code bracketsCount} closing brackets. Using recursive algorithm.
     * bracketsCount must be a positive number.
     *
     * @param bracketsCount number of opening and closing brackets, must be positive
     * @return count of correct variants of bracket expressions
     * @throws NegativeNumberException when {@code bracketsCount} is negative
     */
    public int countVariants(int bracketsCount) throws NegativeNumberException {
        if (bracketsCount < 0) {
            throw new NegativeNumberException();
        }
        if (bracketsCount == 0) {
            return 0;
        }

        AdditionalCounters counters = new AdditionalCounters(bracketsCount * 2);
        appendBracket(counters, 0);

        return counters.variantsCount;
    }

    /**
     * Recursive function for counting correct bracket expressions variants. Quits when opening brackets are more than half or
     * closing brackets are more than opening, or when all brackets are correct.
     * If not quits, tries to add opening and closing brackets sequentially.
     *
     * @param counters       instance of class that contains additional counters
     * @param recursionDepth level of recursion
     */
    private void appendBracket(AdditionalCounters counters, int recursionDepth) {
        if (isInIncorrectState(counters)) {
            return;
        }

        boolean bracketsWereBuilt = recursionDepth == counters.bracketExpressionLength;
        if (bracketsWereBuilt) {
            counters.variantsCount++;
            return;
        }

        counters.leftBracketsCount++;
        appendBracket(counters, recursionDepth + 1);
        counters.leftBracketsCount--;

        boolean leftBracketsMoreThanRight = counters.rightBracketsCount < counters.leftBracketsCount;
        if (leftBracketsMoreThanRight) {
            counters.rightBracketsCount++;
            appendBracket(counters, recursionDepth + 1);
            counters.rightBracketsCount--;
        }
    }

    private boolean isInIncorrectState(AdditionalCounters counters) {
        boolean rightBracketsMoreThanLeft = counters.rightBracketsCount > counters.leftBracketsCount;
        boolean tooManyLeftBrackets = counters.leftBracketsCount > counters.bracketExpressionLength / 2;

        return tooManyLeftBrackets || rightBracketsMoreThanLeft;
    }

    /**
     * This class contains some counters, so they all can be passed to a recursive method within one parameter.
     */
    private class AdditionalCounters {
        private int variantsCount;
        private int bracketExpressionLength;
        private int leftBracketsCount;
        private int rightBracketsCount;

        AdditionalCounters(int bracketExpressionLength) {
            this.bracketExpressionLength = bracketExpressionLength;
        }
    }
}
