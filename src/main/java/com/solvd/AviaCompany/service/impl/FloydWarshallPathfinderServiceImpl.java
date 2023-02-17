package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.service.interfaces.PathfinderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshallPathfinderServiceImpl implements PathfinderService {

    private static final int INF = Integer.MAX_VALUE / 2;
    private static final int OFFSET = 1;

    private static final Logger logger = LogManager.getLogger();

    public List<Integer> findPath(int[][] graph, int src, int dest) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        // Initialize dist and next matrices
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (dist[i][j] != INF) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Apply Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Construct the shortest path between src and dest
        List<Integer> path = new ArrayList<>();
        if (dist[src][dest] == INF) {
            return path;
        }
        path.add(src);
        while (src != dest) {
            src = next[src][dest];
            path.add(src);
        }
        return path;
    }

    public void testCase() {
        int[][] testGraph = {
                {0, 7, INF, INF, 3},
                {INF, 0, INF, 2, INF},
                {INF, 4, 0, 1, INF},
                {-3, INF, INF, 0, INF},
                {2, INF, 4, INF, 0}
        };
        PathfinderService pathfinderService = new FloydWarshallPathfinderServiceImpl();
        List<Integer> path = pathfinderService.findPath(testGraph, 3, 2);
        FloydWarshallPathfinderServiceImpl.printShortestPath(path, 3, 2);
    }

    public static void printShortestPath(List<Integer> path, int src, int dest) {
        if (path.isEmpty()) {
            logger.info("There is no path between " + src + " and " + dest);
        } else {
            StringBuilder builder = new StringBuilder("Shortest path between " + src + " and " + dest + ": ");
            for (int i = 0; i < path.size(); i++) {
                builder.append(path.get(i) + OFFSET);
                if (i != path.size() - 1) {
                    builder.append(" -> ");
                }
            }
            logger.info(builder);
        }
    }
}
