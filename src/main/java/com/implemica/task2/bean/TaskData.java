package com.implemica.task2.bean;

import java.util.Arrays;

/**
 * Created by Евгений on 23.06.2017.
 */
public class TaskData {
    private int testsCount;
    private int citiesCount;
    private City[] cities;
    private Test[] tests;

    public TaskData(int testsCount, int citiesCount, City[] cities, Test[] tests) {
        this.testsCount = testsCount;
        this.citiesCount = citiesCount;
        this.cities = cities;
        this.tests = tests;
    }

    public int getTestsCount() {
        return testsCount;
    }

    public int getCitiesCount() {
        return citiesCount;
    }

    public City[] getCities() {
        return cities;
    }

    public Test[] getTests() {
        return tests;
    }

    public int getCityIndex(String cityName) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getName().equals(cityName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskData taskData = (TaskData) o;

        if (testsCount != taskData.testsCount) return false;
        if (citiesCount != taskData.citiesCount) return false;
        if (!Arrays.equals(cities, taskData.cities)) return false;
        return Arrays.equals(tests, taskData.tests);
    }

    @Override
    public int hashCode() {
        int result = testsCount;
        result = 31 * result + citiesCount;
        result = 31 * result + Arrays.hashCode(cities);
        result = 31 * result + Arrays.hashCode(tests);
        return result;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "testsCount=" + testsCount +
                ", citiesCount=" + citiesCount +
                ", cities=" + Arrays.toString(cities) +
                ", tests=" + Arrays.toString(tests) +
                '}';
    }
}
