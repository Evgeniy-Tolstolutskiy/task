package com.implemica.task2.validator;

import com.implemica.task2.exception.InvalidFileFormatException;
import org.junit.Test;

/**
 * Created by Евгений on 28.06.2017.
 */
public class TaskDataValidatorTest {
    private TaskDataValidator validator = new TaskDataValidator();

    @Test(expected = InvalidFileFormatException.class)
    public void testValidateTestCountNegative() throws InvalidFileFormatException {
        validator.validateTestsCount("-1");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidateTestCountGreaterThanMax() throws InvalidFileFormatException {
        validator.validateTestsCount("12");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidateCitiesCountNegative() throws InvalidFileFormatException {
        validator.validateCitiesCount("-1");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidateCitiesCountGreaterThanMax() throws InvalidFileFormatException {
        validator.validateCitiesCount("12000");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidateCityName() throws InvalidFileFormatException {
        validator.validateCityName("NotExistingCity");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidateTransportationCost() throws InvalidFileFormatException {
        validator.validateTransportationCost("0");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidatePathsCountNegative() throws InvalidFileFormatException {
        validator.validatePathsCount("-1");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testValidatePathsCountGreaterThanMax() throws InvalidFileFormatException {
        validator.validatePathsCount("120");
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testPassNotANumber() throws InvalidFileFormatException {
        validator.validatePathsCount("string instead of integer");
    }
}
