// Ryan Hecht
// 251220567

package asn3;

import java.io.IOException;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    WeightedGraph graph;

    // We exit in the case of an error because there's no graph to work with!
    try {
      graph = GraphLoader.loadUndirectedWeightedGraph("/mst_graph_medium.txt");
    } catch (IOException e) {
      System.err.println("There was an error reading the graph file: " + e.getMessage());
      return;
    }

    graph.printAdjacencyList();

    List<WeightedEdge<Integer>> minimumSpanningTree = graph.generateMSTPrim();
    minimumSpanningTree.forEach(System.out::println);

    int totalWeight = minimumSpanningTree.stream().mapToInt(WeightedEdge::weight).sum();

    System.out.println("Total weight of the minimum spanning tree: " + totalWeight);
  }
}
