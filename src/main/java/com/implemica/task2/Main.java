package com.implemica.task2;

import com.implemica.task2.bean.TaskData;
import com.implemica.task2.exception.InvalidFileFormatException;
import com.implemica.task2.exception.InvalidGraphDataException;
import com.implemica.task2.reader.DataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Евгений on 27.06.2017.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("file.txt");
        DataReader reader = new DataReader();
        TaskData taskData;
        try {
            taskData = reader.readData(new FileInputStream(file));
            Graph graph = Graph.initGraph(taskData);
            MinimalCostPathsFinder finder = new MinimalCostPathsFinder();
            List<int[]> minimalCosts = finder.findMinimalCosts(taskData, graph);
            for (int[] testOutput : minimalCosts) {
                for (int cost : testOutput) {
                    System.out.println(cost);
                }
                System.out.println();
            }
        } catch (InvalidFileFormatException | InvalidGraphDataException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Wrong data was given");
        }

    }
}
