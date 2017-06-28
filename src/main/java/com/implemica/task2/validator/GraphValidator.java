package com.implemica.task2.validator;

import com.implemica.task2.Graph;
import com.implemica.task2.exception.InvalidGraphDataException;

/**
 * Created by Евгений on 28.06.2017.
 */
public class GraphValidator {

    private static final int MAX_PATH_COST = 200000;

    /**
     * Assumes that eac path cost in given {@link Graph} object is less than 200000.
     *
     * @param graph
     * @throws InvalidGraphDataException
     */
    public void validateTransportationCosts(Graph graph) throws InvalidGraphDataException {
        for (int i = 0; i < graph.getNodesCount(); i++) {
            for (int j = 0; j < graph.getNodesCount(); j++) {
                if (graph.getPathCost(i, j) > MAX_PATH_COST) {
                    throw new InvalidGraphDataException();
                }
            }
        }
    }
}
