package com.implemica.task2.reader;

import com.implemica.task2.bean.City;
import com.implemica.task2.bean.TaskData;
import com.implemica.task2.bean.Test;
import com.implemica.task2.exception.InvalidFileFormatException;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Евгений on 28.06.2017.
 */
public class DataReaderTest {
    private TaskData preparedTaskData;
    private DataReader reader = new DataReader();

    @Before
    public void init() {
        int testsCount = 1;
        int citiesCount = 3;

        City[] cities = new City[citiesCount];
        Test[] tests = new Test[testsCount];

        cities[0] = new City("gdansk", 2, new int[]{2, 3}, new int[]{1, 3});
        cities[1] = new City("bydgoszcz", 2, new int[]{1, 3}, new int[]{1, 1});
        cities[2] = new City("torun", 2, new int[]{1, 2}, new int[]{3, 1});

        tests[0] = new Test(1, new String[]{"gdansk"}, new String[]{"torun"});

        preparedTaskData = new TaskData(testsCount, citiesCount, cities, tests);
    }

    @org.junit.Test
    public void testDataReader() throws FileNotFoundException, InvalidFileFormatException {
        TaskData actualTaskData = reader.readData(new FileInputStream(getClass().getResource("/testFile.txt").getFile()));
        assertEquals(preparedTaskData, actualTaskData);
    }
}
