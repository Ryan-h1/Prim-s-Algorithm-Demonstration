package asn3;

import java.util.*;

public class WeightedGraph {
  private final Map<Integer, List<WeightedEdgeSingleTarget<Integer>>> adjacencyList;
  private final int numberOfVertices;

  public WeightedGraph(int numberOfVertices) {
    adjacencyList = new HashMap<>();
    this.numberOfVertices = numberOfVertices;
    for (int i = 1; i <= numberOfVertices; i++) {
      adjacencyList.put(i, new ArrayList<>());
    }
  }

  public void addWeightedEdge(int source, int target, int weight) {
    adjacencyList.get(source).add(new WeightedEdgeSingleTarget<>(target, weight));
    // For directed graphs, we would comment the following line
    adjacencyList.get(target).add(new WeightedEdgeSingleTarget<>(source, weight));
  }

  public List<WeightedEdge<Integer>> generateMSTPrim() {
    List<WeightedEdge<Integer>> mst = new ArrayList<>();
    boolean[] inMST = new boolean[numberOfVertices + 1];
    PriorityQueue<VertexKey> pq = new PriorityQueue<>();
    int[] key = new int[numberOfVertices + 1];
    int[] parent = new int[numberOfVertices + 1];
    Arrays.fill(key, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);

    // Start with vertex 1
    key[1] = 0;
    pq.offer(new VertexKey(1, 0));

    while (!pq.isEmpty()) {
      int u = pq.poll().vertex;
      inMST[u] = true;

      // Iterate through all the adjacent vertices of u
      for (WeightedEdgeSingleTarget<Integer> edge : adjacencyList.get(u)) {
        int v = edge.target;
        int weight = edge.weight;

        // If v is not in MST and weight of u-v is smaller than the current key of v
        if (!inMST[v] && weight < key[v]) {
          parent[v] = u;
          key[v] = weight;
          pq.offer(new VertexKey(v, key[v]));
        }
      }
    }

    // Build the MST result
    for (int i = 2; i <= numberOfVertices; i++) { // Skip the starting vertex
      if (parent[i] > 0) { // i.e., there is an edge from parent[i] to i
        mst.add(new WeightedEdge<>(parent[i], i, key[i]));
      }
    }

    return mst;
  }

  public void printAdjacencyList() {
    adjacencyList.forEach(
        (vertex, edges) -> {
          System.out.print("Vertex " + vertex + ": ");
          edges.forEach(
              edge -> System.out.print(" -> " + edge.target + " (Weight: " + edge.weight + ")"));
          System.out.println();
        });
  }
}
