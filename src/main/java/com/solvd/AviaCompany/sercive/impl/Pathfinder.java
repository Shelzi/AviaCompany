package com.solvd.AviaCompany.sercive.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Pathfinder {

    private static final int INF = Integer.MAX_VALUE / 2;
    private static final int OFFSET = 1;

    private static final Logger logger = LogManager.getLogger();

    private Map<String, int[][]> findPathsByFloydWarshall(int[][] input) {
        Map<String, int[][]> resultMap = new HashMap<>();
        int[][] resultWeights = input;
        int[][] resultPaths = fillDefaultPaths(input);
        for (int k = 0; k < input.length; k++)
            for (int j = 0; j < input.length; j++)
                for (int i = 0; i < input.length; i++) {
                    if (resultWeights[i][k] + resultWeights[k][j] < resultWeights[i][j]) {
                        resultWeights[i][j] = resultWeights[i][k] + resultWeights[k][j];
                        resultPaths[i][j] = k + OFFSET;
                    }
                }
        resultMap.put("weight", resultWeights);
        resultMap.put("path", resultPaths);
        return resultMap;
    }

    private int[][] fillDefaultPaths(int[][] defPaths) {
        int[][] resultPaths = new int[defPaths.length][defPaths.length];
        for (int i = 0; i < defPaths.length; i++) {
            for (int j = 0; j < defPaths.length; j++) {
                resultPaths[i][j] = j + OFFSET;
            }
        }
        return resultPaths;
    }

    public static void main(String[] args) {
        int[][] testGraph = {
                {0, 7, INF, INF, 3},
                {INF, 0, INF, 2, INF},
                {INF, 4, 0, 1, INF},
                {-3, INF, INF, 0, INF},
                {2, INF, 4, INF, 0}
        };
        Pathfinder pathfinder = new Pathfinder();
        Map<String, int[][]> resultMap = pathfinder.findPathsByFloydWarshall(testGraph);
        printMatrix(resultMap.get("weight"));
        printMatrix(resultMap.get("path"));
    }

    public static void printMatrix(int input[][]) {
        for (int i = 0; i < input.length; ++i) {
            for (int j = 0; j < input.length; ++j) {
                System.out.print(input[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
