package com.bogdaniancu.computerscience.graphs.dijkstra;


import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Node {
    private String name;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(getName(), node.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", shortestPath=" + shortestPath.stream().map((Node n)-> n.name).collect(Collectors.toList()) +
                ", distance=" + distance +
                ", adjacentNodes=" + adjacentNodes  +
                '}';
    }
}



