package com.implemica.task2.validator;

import com.implemica.task2.Graph;
import com.implemica.task2.bean.City;
import com.implemica.task2.bean.TaskData;
import com.implemica.task2.bean.Test;
import com.implemica.task2.exception.InvalidGraphDataException;
import org.junit.Before;

/**
 * Created by Евгений on 28.06.2017.
 */
public class GraphValidatorTest {
    private Graph graph;
    private TaskData preparedTaskData;

    @Before
    public void init() {
        int testsCount = 1;
        int citiesCount = 3;

        City[] cities = new City[citiesCount];
        Test[] tests = new Test[testsCount];

        cities[0] = new City("gdansk", 2, new int[]{2, 3}, new int[]{200001, 200001});
        cities[1] = new City("bydgoszcz", 2, new int[]{1, 3}, new int[]{200001, 200001});
        cities[2] = new City("torun", 2, new int[]{1, 2}, new int[]{200001, 200001});

        tests[0] = new Test(1, new String[]{"gdansk"}, new String[]{"torun"});

        preparedTaskData = new TaskData(testsCount, citiesCount, cities, tests);
    }

    @org.junit.Test(expected = InvalidGraphDataException.class)
    public void testValidator() throws InvalidGraphDataException {
        graph = Graph.initGraph(preparedTaskData);
    }
}
