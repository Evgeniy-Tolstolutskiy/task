package com.implemica.task2.validator;

import com.implemica.task2.exception.InvalidFileFormatException;

/**
 * Created by Евгений on 27.06.2017.
 */
public class TaskDataValidator {

    private static final int MAX_TESTS_COUNT = 10;
    private static final int MAX_CITIES_COUNT = 10000;
    private static final int MAX_CITY_NAME_LENGTH = 10;
    private static final int MINIMAL_TRANSPORTATION_COST = 1;
    private static final int MAX_PATHS_COUNT = 100;

    /**
     * Assumes that given {@code testsCountString} is a number, bigger than 0 and less or equal to 10.
     *
     * @param testsCountString
     * @throws InvalidFileFormatException
     */
    public void validateTestsCount(String testsCountString) throws InvalidFileFormatException {
        int testsCount = tryParseToInt(testsCountString);
        if (testsCount < 0 || testsCount > MAX_TESTS_COUNT) {
            throw new InvalidFileFormatException(String.format("Invalid value of tests count was given: %d.", testsCount));
        }
    }

    /**
     * Assumes that given {@code citiesCountString} is a number, bigger than 0 and less or equal to 10000.
     *
     * @param citiesCountString
     * @throws InvalidFileFormatException
     */
    public void validateCitiesCount(String citiesCountString) throws InvalidFileFormatException {
        int citiesCount = tryParseToInt(citiesCountString);
        if (citiesCount < 0 || citiesCount > MAX_CITIES_COUNT) {
            throw new InvalidFileFormatException(String.format("Invalid value of cities count was given: %d.", citiesCount));
        }
    }

    /**
     * Assumes that given {@code cityName} length is less or equal to 10.
     *
     * @param cityName
     * @throws InvalidFileFormatException
     */
    public void validateCityName(String cityName) throws InvalidFileFormatException {
        if (cityName.length() > MAX_CITY_NAME_LENGTH) {
            throw new InvalidFileFormatException(String.format("City name '%s' contains more that 10 characters.", cityName));
        }
    }

    /**
     * Assumes that given {@code transportationCostString} is a number and bigger or equal to 1.
     *
     * @param transportationCostString
     * @throws InvalidFileFormatException
     */
    public void validateTransportationCost(String transportationCostString) throws InvalidFileFormatException {
        int transportationCost = tryParseToInt(transportationCostString);
        if (transportationCost < MINIMAL_TRANSPORTATION_COST) {
            throw new InvalidFileFormatException(String.format("Invalid value of transportation cost was given: %d.", transportationCost));
        }
    }

    /**
     * Assumes that given {@code pathsCountString} is a number, bigger than 0 and less or equal to 100.
     *
     * @param pathsCountString
     * @throws InvalidFileFormatException
     */
    public void validatePathsCount(String pathsCountString) throws InvalidFileFormatException {
        int pathsCount = tryParseToInt(pathsCountString);
        if (pathsCount < 0 || pathsCount > MAX_PATHS_COUNT) {
            throw new InvalidFileFormatException(String.format("Invalid value of paths count was given: %d.", pathsCount));
        }
    }

    private int tryParseToInt(String s) throws InvalidFileFormatException {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException ex) {
            throw new InvalidFileFormatException(String.format("%s is not a number.", s));
        }
    }
}
