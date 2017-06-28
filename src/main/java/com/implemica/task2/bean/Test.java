package com.implemica.task2.bean;

import java.util.Arrays;

/**
 * Created by Евгений on 24.06.2017.
 */
public class Test {
    private int pathsCount;
    private String[] sourceCityNames;
    private String[] destinationCityNames;

    public Test(int pathsCount, String[] sourceCityNames, String[] destinationCityNames) {
        this.pathsCount = pathsCount;
        this.sourceCityNames = sourceCityNames;
        this.destinationCityNames = destinationCityNames;
    }

    public int getPathsCount() {
        return pathsCount;
    }

    public String[] getSourceCityNames() {
        return sourceCityNames;
    }

    public String[] getDestinationCityNames() {
        return destinationCityNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (pathsCount != test.pathsCount) return false;
        if (!Arrays.equals(sourceCityNames, test.sourceCityNames)) return false;
        return Arrays.equals(destinationCityNames, test.destinationCityNames);
    }

    @Override
    public int hashCode() {
        int result = pathsCount;
        result = 31 * result + Arrays.hashCode(sourceCityNames);
        result = 31 * result + Arrays.hashCode(destinationCityNames);
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "pathsCount=" + pathsCount +
                ", sourceCityNames=" + Arrays.toString(sourceCityNames) +
                ", destinationCityNames=" + Arrays.toString(destinationCityNames) +
                '}';
    }
}
