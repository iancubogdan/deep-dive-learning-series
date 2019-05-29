package com.bogdaniancu.computerscience.graphs.dijkstra;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
}
