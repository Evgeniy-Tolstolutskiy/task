package com.implemica.task2;

import com.implemica.task2.bean.TaskData;
import com.implemica.task2.exception.InvalidGraphDataException;
import com.implemica.task2.validator.GraphValidator;

/**
 * Created by Евгений on 26.06.2017.
 * <p>
 * This class is representing graph based on some {@link TaskData} object.
 */
public class Graph {

    private int[][] distances;

    private Graph(int[][] distances) {
        this.distances = distances;
    }

    /**
     * Does minimal distances computing with Floyd-Warshall algorithm and also does validation of these distances. Throes exception if validation fails.
     *
     * @param taskData
     * @return graph with computed distances
     * @throws InvalidGraphDataException when validation fails
     */
    public static Graph initGraph(TaskData taskData) throws InvalidGraphDataException {
        int[][] distances = new int[taskData.getCitiesCount()][taskData.getCitiesCount()];
        initDistances(distances);

        for (int i = 0; i < taskData.getCitiesCount(); i++) {
            for (int j = 0; j < taskData.getCities()[i].getNeighboursCount(); j++) {
                int realCityIndex = taskData.getCities()[i].getCityIndexes()[j] - 1;
                distances[i][realCityIndex] = taskData.getCities()[i].getTransportationCosts()[j];
            }
        }

        floydWarshall(taskData.getCitiesCount(), distances);

        Graph graph = new Graph(distances);
        new GraphValidator().validateTransportationCosts(graph);

        return graph;
    }

    private static void floydWarshall(int nodesCount, int[][] distances) {
        for (int k = 0; k < nodesCount; k++) {
            for (int i = 0; i < nodesCount; i++) {
                for (int j = 0; j < nodesCount; j++) {
                    int oldValue = distances[i][j];
                    int newValue = sumWithInfinityCheck(distances[i][k], distances[k][j]);
                    if (oldValue > newValue) {
                        distances[i][j] = newValue;
                    }
                }
            }
        }
    }

    private static int sumWithInfinityCheck(int a, int b) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return a + b;
    }

    private static void initDistances(int[][] distances) {
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public int getPathCost(int source, int destination) {
        return distances[source][destination];
    }

    public int getNodesCount() {
        return distances.length;
    }
}
