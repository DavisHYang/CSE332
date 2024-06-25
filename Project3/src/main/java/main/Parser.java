package main;

import cse332.graph.GraphUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    /**
     * Parse an adjacency matrix into an adjacency list.
     * @param adjMatrix Adjacency matrix
     * @return Adjacency list of maps from node to weight
     */
    public static List<Map<Integer, Integer>> parse(int[][] adjMatrix) {
        List<Map<Integer, Integer>> res = new ArrayList<>();

        //fill the list with empty hashmaps
        for(int i = 0; i < adjMatrix.length; i++) {
            res.add(new HashMap<Integer, Integer>());
        }

        //fill the list with the adjacency list corresponding to the matrix
        for(int u = 0; u < adjMatrix.length; u++) {
            for(int v = 0; v < adjMatrix[u].length; v++) {
                //checks if the edge exists from u to v
                if(adjMatrix[u][v] != GraphUtil.INF) {
                    res.get(u).put(v, adjMatrix[u][v]);
                }
            }
        }
        return res;
    }

    /**
     * Parse an adjacency matrix into an adjacency list with incoming edges instead of outgoing edges.
     * @param adjMatrix Adjacency matrix
     * @return Adjacency list of maps from node to weight with incoming edges
     */
    public static List<Map<Integer, Integer>> parseInverse(int[][] adjMatrix) {
        List<Map<Integer, Integer>> res = new ArrayList<>();

        //fill the list with empty hashmaps
        for(int i = 0; i < adjMatrix.length; i++) {
            res.add(new HashMap<Integer, Integer>());
        }

        //fill the list with the adjacency list corresponding to the matrix
        for(int u = 0; u < adjMatrix.length; u++) {
            for(int v = 0; v < adjMatrix[u].length; v++) {
                //checks if the edge exists from u to v
                if(adjMatrix[u][v] != GraphUtil.INF) {
                    // put u and cost into map of v
                    res.get(v).put(u, adjMatrix[u][v]);
                }
            }
        }
        return res;
    }

}
