package com.implemica.task2;

import com.implemica.task2.bean.TaskData;
import com.implemica.task2.bean.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 23.06.2017.
 */
public class MinimalCostPathsFinder {

    /**
     * Finds needed minimal costs specified in {@link TaskData} object and already computed in {@link Graph} object.
     *
     * @param taskData
     * @param graph
     * @return
     */
    public List<int[]> findMinimalCosts(TaskData taskData, Graph graph) {
        List<int[]> result = new ArrayList<>();
        for (Test test : taskData.getTests()) {
            int[] testOutput = new int[test.getPathsCount()];
            for (int i = 0; i < test.getPathsCount(); i++) {
                String sourceCityName = test.getSourceCityNames()[i];
                String destinationCityName = test.getDestinationCityNames()[i];
                testOutput[i] = graph.getPathCost(taskData.getCityIndex(sourceCityName), taskData.getCityIndex(destinationCityName));
            }
            result.add(testOutput);
        }
        return result;
    }
}
