// Ryan Hecht
// 251220567

package asn3;

import java.util.*;

/***
 * This class represents a weighted, undirected graph using an internal adjacency list representation. The graph is
 * 1-indexed, meaning that the vertices are numbered from 1 to n, where n is the number of vertices.
 * <p>
 * Note: This class is tightly coupled and should not be considered a general-purpose graph implementation. It was
 * made specifically for the purpose of implementing Prim's algorithm for finding the minimum spanning tree of a graph.
 */
public class WeightedGraph {
  private static final int STARTING_VERTEX = 1;

  private final Map<Integer, List<OutgoingWeightedEdge<Integer>>> adjacencyList;
  private final int numberOfVertices;

  public WeightedGraph(int numberOfVertices) {
    adjacencyList = new HashMap<>();
    this.numberOfVertices = numberOfVertices;
    for (int i = 1; i <= numberOfVertices; i++) {
      adjacencyList.put(i, new ArrayList<>());
    }
  }

  public void addWeightedEdge(int source, int target, int weight) {
    adjacencyList.get(source).add(new OutgoingWeightedEdge<>(target, weight));
    // For directed graphs, we would comment the following line
    adjacencyList.get(target).add(new OutgoingWeightedEdge<>(source, weight));
  }

  public List<WeightedEdge<Integer>> generateMSTPrim() {
    List<WeightedEdge<Integer>> mst = new ArrayList<>();
    boolean[] inMST = new boolean[numberOfVertices + 1];
    Heap heap = new Heap(new int[numberOfVertices + 1]); // +1 for 1-based indexing
    int[] parent = new int[numberOfVertices + 1];

    heap.decreaseKey(STARTING_VERTEX, 0);

    while (!heap.isEmpty()) {
      // Extract the minimum vertex id
      int u = heap.minId();
      heap.deleteMin();
      inMST[u] = true; // Include it in MST

      if (parent[u] > 0) {
        mst.add(new WeightedEdge<>(parent[u], u, heap.key(u)));
      }

      // For each adjacent vertex v of u
      for (OutgoingWeightedEdge<Integer> edge : adjacencyList.get(u)) {
        int v = edge.target();
        int weight = edge.weight();

        // If v is not in MST and weight of edge u-v is smaller than the key of v
        if (!inMST[v] && weight < heap.key(v)) {
          parent[v] = u;
          heap.decreaseKey(v, weight); // Update the key in the heap
        }
      }
    }

    return mst;
  }

  public void printAdjacencyList() {
    adjacencyList.forEach(
        (vertex, edges) -> {
          System.out.print("Vertex " + vertex + ": ");
          edges.forEach(
              edge ->
                  System.out.print(
                      " -> " + edge.target() + " (Weight: " + edge.weight() + ")"));
          System.out.println();
        });
  }
}
