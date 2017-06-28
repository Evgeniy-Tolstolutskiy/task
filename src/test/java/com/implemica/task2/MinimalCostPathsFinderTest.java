package com.implemica.task2;

import com.implemica.task2.bean.City;
import com.implemica.task2.bean.TaskData;
import com.implemica.task2.bean.Test;
import com.implemica.task2.exception.InvalidGraphDataException;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Евгений on 28.06.2017.
 */
public class MinimalCostPathsFinderTest {
    private static final int EXPECTED_MINIMAL_PATH_COST = 2;
    private TaskData preparedTaskData;
    private Graph preparedGraph;
    private MinimalCostPathsFinder finder = new MinimalCostPathsFinder();
    private List<int[]> expectedMinimalCosts = new ArrayList<>();

    @Before
    public void init() throws InvalidGraphDataException {
        int testsCount = 1;
        int citiesCount = 3;

        City[] cities = new City[citiesCount];
        Test[] tests = new Test[testsCount];

        cities[0] = new City("gdansk", 2, new int[]{2, 3}, new int[]{1, 3});
        cities[1] = new City("bydgoszcz", 2, new int[]{1, 3}, new int[]{1, 1});
        cities[2] = new City("torun", 2, new int[]{1, 2}, new int[]{3, 1});

        tests[0] = new Test(1, new String[]{"gdansk"}, new String[]{"torun"});

        preparedTaskData = new TaskData(testsCount, citiesCount, cities, tests);
        preparedGraph = Graph.initGraph(preparedTaskData);

        expectedMinimalCosts.add(new int[]{EXPECTED_MINIMAL_PATH_COST});
    }

    @org.junit.Test
    public void testFinder() {
        List<int[]> actualMinimalCosts = finder.findMinimalCosts(preparedTaskData, preparedGraph);
        assertEquals(expectedMinimalCosts.get(0)[0], actualMinimalCosts.get(0)[0]);
    }
}
