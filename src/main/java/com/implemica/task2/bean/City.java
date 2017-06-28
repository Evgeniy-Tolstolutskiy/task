package com.implemica.task2.bean;

import java.util.Arrays;

/**
 * Created by Евгений on 24.06.2017.
 */
public class City {
    private String name;
    private int neighboursCount;
    private int[] cityIndexes;
    private int[] transportationCosts;

    public City(String name, int neighboursCount, int[] cityIndexes, int[] transportationCosts) {
        this.name = name;
        this.neighboursCount = neighboursCount;
        this.cityIndexes = cityIndexes;
        this.transportationCosts = transportationCosts;
    }

    public String getName() {
        return name;
    }

    public int getNeighboursCount() {
        return neighboursCount;
    }

    public int[] getCityIndexes() {
        return cityIndexes;
    }

    public int[] getTransportationCosts() {
        return transportationCosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (neighboursCount != city.neighboursCount) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (!Arrays.equals(cityIndexes, city.cityIndexes)) return false;
        return Arrays.equals(transportationCosts, city.transportationCosts);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + neighboursCount;
        result = 31 * result + Arrays.hashCode(cityIndexes);
        result = 31 * result + Arrays.hashCode(transportationCosts);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", neighboursCount=" + neighboursCount +
                ", cityIndexes=" + Arrays.toString(cityIndexes) +
                ", transportationCosts=" + Arrays.toString(transportationCosts) +
                '}';
    }
}
