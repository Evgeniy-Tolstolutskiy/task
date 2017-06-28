package com.implemica.task1;

import com.implemica.task1.exception.NegativeNumberException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Евгений on 22.06.2017.
 */
public class CorrectBracketVariantsCounterTest {
    private CorrectBracketVariantsCounter correctBracketVariantsCounter = new CorrectBracketVariantsCounter();

    @Test
    public void testForZeroBrackets() throws NegativeNumberException {
        testForNBrackets(0, 0);
    }

    /**
     * Expected result for this test is 2, because possible brackets combinations for 2 are ()(), (())
     *
     * @throws NegativeNumberException
     */
    @Test
    public void testForTwoBrackets() throws NegativeNumberException {
        testForNBrackets(2, 2);
    }

    /**
     * Expected result for this test is 5, because possible brackets combinations for 3 are ((())), ()(()), ()()(), (())(), (()())
     *
     * @throws NegativeNumberException
     */
    @Test
    public void testForThreeBrackets() throws NegativeNumberException {
        testForNBrackets(3, 5);
    }

    /**
     * Expected result for this test is 14, because possible brackets combinations for 4 are (((()))), ()((())), (()(())), ((()())), ((())()),
     * ()()()(), (()()()), ()(())(), ((()))(), (()())(), ()(()()), (())()(), ()()(()), (())(())
     *
     * @throws NegativeNumberException
     */
    @Test
    public void testForFourBrackets() throws NegativeNumberException {
        testForNBrackets(4, 14);
    }

    @Test(expected = NegativeNumberException.class)
    public void testForNegativeNumberHandling() throws NegativeNumberException {
        correctBracketVariantsCounter.countVariants(-1);
    }

    private void testForNBrackets(int n, int expectedResult) throws NegativeNumberException {
        int variants = correctBracketVariantsCounter.countVariants(n);
        assertEquals(expectedResult, variants);
    }
}
