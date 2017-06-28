package com.implemica.task2.reader;

import com.implemica.task2.bean.City;
import com.implemica.task2.bean.TaskData;
import com.implemica.task2.bean.Test;
import com.implemica.task2.exception.InvalidFileFormatException;
import com.implemica.task2.validator.TaskDataValidator;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Евгений on 23.06.2017.
 * <p>
 * Reads task data from {@link InputStream} source.
 */
public class DataReader {
    private TaskDataValidator validator = new TaskDataValidator();

    /**
     * Reads data from {@link InputStream} and returns {@link TaskData} object. Does validation, and if fails, throws exception.
     *
     * @param source source to read
     * @return {@link TaskData} object
     * @throws InvalidFileFormatException when validation fails
     */
    public TaskData readData(InputStream source) throws InvalidFileFormatException {
        try {
            Scanner scanner = new Scanner(source);

            String testsCountString = scanner.nextLine();
            validator.validateTestsCount(testsCountString);
            int testsCount = Integer.valueOf(testsCountString);

            String citiesCountString = scanner.nextLine();
            validator.validateCitiesCount(citiesCountString);
            int citiesCount = Integer.valueOf(citiesCountString);

            City[] cities = readCitiesInfo(scanner, citiesCount);
            Test[] tests = readTestsInfo(scanner, testsCount);

            return new TaskData(testsCount, citiesCount, cities, tests);
        } catch (NoSuchElementException ex) {
            throw new InvalidFileFormatException("Data format is wrong.");
        }
    }

    private City[] readCitiesInfo(Scanner scanner, int numberOfCities) throws InvalidFileFormatException {
        City[] cities = new City[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            String cityName = scanner.nextLine();
            validator.validateCityName(cityName);

            int neighboursNumber = Integer.valueOf(scanner.nextLine());
            int[] cityIndexes = new int[neighboursNumber];
            int[] transportationCosts = new int[neighboursNumber];
            for (int j = 0; j < neighboursNumber; j++) {
                String[] neighbourDescription = scanner.nextLine().split(" ");
                cityIndexes[j] = Integer.valueOf(neighbourDescription[0]);

                String transportationCostString = neighbourDescription[1];
                validator.validateTransportationCost(transportationCostString);
                transportationCosts[j] = Integer.valueOf(transportationCostString);
            }
            cities[i] = new City(cityName, neighboursNumber, cityIndexes, transportationCosts);
        }

        return cities;
    }

    private Test[] readTestsInfo(Scanner scanner, int numberOfTests) throws InvalidFileFormatException {
        Test[] tests = new Test[numberOfTests];
        for (int i = 0; i < numberOfTests; i++) {
            String pathsCountString = scanner.nextLine();
            validator.validatePathsCount(pathsCountString);
            int pathsCount = Integer.valueOf(pathsCountString);

            String[] sourceCityNames = new String[pathsCount];
            String[] destinationCityNames = new String[pathsCount];
            for (int j = 0; j < pathsCount; j++) {
                String[] testDescription = scanner.nextLine().split(" ");
                sourceCityNames[j] = testDescription[0];
                destinationCityNames[j] = testDescription[1];
            }
            scanner.nextLine();
            tests[i] = new Test(pathsCount, sourceCityNames, destinationCityNames);
        }

        return tests;
    }
}
