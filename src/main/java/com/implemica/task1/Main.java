package com.implemica.task1;

import com.implemica.task1.exception.NegativeNumberException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Евгений on 23.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();

        CorrectBracketVariantsCounter counter = new CorrectBracketVariantsCounter();
        try {
            int variants = counter.countVariants(n);
            System.out.println(String.format("Correct bracket expressions variants count: %d", variants));
        } catch (NegativeNumberException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, String.format("Positive number expected, but %d was given", n));
        }
    }
}
