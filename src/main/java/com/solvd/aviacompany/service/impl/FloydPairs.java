package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FloydPairs {
    private static final IntIntPair INF = new IntIntPair(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
    private static final int OFFSET = 1;
    private static final Logger logger = LogManager.getLogger();
    private static final CityService cityService = new CityServiceImpl();



    public List<Integer> findPath(IntIntPair[][] graph, int src, int dest, boolean a, List<IntIntPair> weights){
        weights.clear();
        int V = graph.length;
        IntIntPair[][] dist = new IntIntPair[V][V];
        int[][] next = new int[V][V];

        // Initialize dist and next matrices
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (!dist[i][j].equals(INF)) {
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
                    if (!dist[i][k].equals(INF) && !dist[k][j].equals(INF)) {
                        boolean check;
                        if (a)
                            check = dist[i][j].getA() > dist[i][k].getA() + dist[k][j].getA();
                        else check = dist[i][j].getB() > dist[i][k].getB() + dist[k][j].getB();
                        if (check) {
                            dist[i][j] = new IntIntPair(dist[i][k].getA() + dist[k][j].getA(), dist[i][k].getB() + dist[k][j].getB());
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
        }

        // Construct the shortest path between src and dest
        List<Integer> path = new ArrayList<>();
        if (dist[src][dest].equals(INF)) {
            return path;
        }
        path.add(src);
        int previous;
        while (src != dest) {
            previous = src;
            src = next[src][dest];
            weights.add(new IntIntPair(dist[previous][src].getA(), dist[previous][src].getB()));
            path.add(src);
        }
        return path;
    }

    public static void printShortestPath(List<Integer> path, List<IntIntPair> weights, int src, int dest) {
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
            builder.append("\n");
            for(int i = 0; i < weights.size(); i++){
                builder.append(weights.get(i).toString());
                if(i != path.size() - 2){
                    builder.append(" -> ");
                }
            }
            logger.info(builder);
        }
    }
}
