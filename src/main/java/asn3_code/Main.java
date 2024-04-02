// Ryan Hecht
// 251220567

package asn3_code;

import java.io.IOException;
import java.util.*;

public class Main {
  //  private static final String MODE = "Maven";
  private static final String MODE = "Stdin";

  public static void main(String[] args) {
    WeightedGraph graph;

    if (MODE.equals("Maven")) {
      // We exit in the case of an error because there's no graph to work with!
      try {
        graph = GraphLoader.loadUndirectedWeightedGraphMaven("/mst_graph_medium.txt");
      } catch (IOException e) {
        System.err.println("There was an error reading the graph file: " + e.getMessage());
        return;
      }
    } else if (MODE.equals("Stdin")) {
      try {
        graph = GraphLoader.loadUndirectedWeightedGraphFromStdin();
      } catch (IOException e) {
        System.err.println(
            "There was an error reading the graph from standard input: " + e.getMessage());
        return;
      }
    } else {
      System.err.println("Invalid mode: " + MODE);
      return;
    }

    graph.printAdjacencyList();

    System.out.println();

    List<WeightedEdge<Integer>> minimumSpanningTree = graph.generateMSTPrim();
    minimumSpanningTree.forEach(System.out::println);

    System.out.println();

    int totalWeight = minimumSpanningTree.stream().mapToInt(WeightedEdge::weight).sum();

    System.out.println("Total weight of the minimum spanning tree: " + totalWeight);
  }
}
