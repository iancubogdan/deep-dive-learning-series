package com.bogdaniancu.computerscience.graphs.bfsdfs;

import java.util.LinkedList;

public class Graph {

    int noOfNodes;
    private LinkedList<Integer> adjListArray[];

    public Graph(int noOfNodes)
    {
        this.noOfNodes = noOfNodes;
        adjListArray = new LinkedList[noOfNodes];
        for(int i = 0; i < noOfNodes; i++){
            adjListArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
    }

    public void BFS(int node) {
        boolean visited[] = new boolean[noOfNodes];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[node] = true;
        queue.add(node);

        while (queue.size() != 0) {
            node = queue.poll();
            System.out.print(node + " ");

            for (Integer newNode : adjListArray[node]) {
                if (!visited[newNode]) {
                    visited[newNode] = true;
                    queue.add(newNode);
                }
            }
        }
    }

    private void DFSUtil(int node, boolean visited[]) {
        visited[node] = true;
        System.out.print(node + " ");

        for (Integer newNode : adjListArray[node]) {
            if (!visited[newNode]) {
                DFSUtil(newNode, visited);
            }
        }
    }

    public void DFS(int node) {
        boolean visited[] = new boolean[noOfNodes];

        DFSUtil(node, visited);
    }

    public void printGraph() {
        for (int i = 0; i < noOfNodes; i++) {
            System.out.println("Adjacency list of vertex "+ i);
            for (Integer dest : adjListArray[i]) {
                System.out.print(" " + dest);
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.printGraph();

        System.out.println("BFS Traversal");
        graph.BFS(0);

        System.out.println("\nDFS Traversal");
        graph.DFS(0);
    }
}
