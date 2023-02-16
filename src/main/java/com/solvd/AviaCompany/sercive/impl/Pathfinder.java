package com.solvd.AviaCompany.sercive.impl;

public class Pathfinder {

    private static final int INF = Integer.MAX_VALUE / 2;

    private int[][] findPathsByFloydWarshall(int input[][]) {
        int[][] result = input;
        for (int k = 0; k < input.length; k++)
            for (int j = 0; j < input.length; j++)
                for (int i = 0; i < input.length; i++) {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (result[i][k] + result[k][j] < result[i][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
        return result;
    }

    public static void main(String[] args) {
        // Assume an adjacency matrix representation
        // Assume vertices are numbered 1,2,…,n
        // The input is a n x n matrix (see README.md)
        int[][] testGraph = {
                {0, 7, INF, INF, 3},
                {INF, 0, INF, 2, INF},
                {INF, 4, 0, 1, INF},
                {-3, INF, INF, 0, INF},
                {3, INF, 4, INF, 0}
        };
        Pathfinder pathfinder = new Pathfinder();
        int[][] result = pathfinder.findPathsByFloydWarshall(testGraph);
        printMatrix(result);
    }

    static void printMatrix(int input[][]) {
        System.out.println("New Matrix: ");
        for (int i = 0; i < input.length; ++i) {
            for (int j = 0; j < input.length; ++j) {
                if (input[i][j] == input.length)
                    System.out.print("I   \t");
                else
                    System.out.print(input[i][j] + "   \t");
            }
            System.out.println();
        }
    }
}
