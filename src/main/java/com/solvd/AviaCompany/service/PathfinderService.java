package com.solvd.AviaCompany.service;

import java.util.List;

public interface PathfinderService {
    List<Integer> findPath(int[][] graph, int src, int dest);
}
