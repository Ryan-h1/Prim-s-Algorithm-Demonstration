// Ryan Hecht
// 251220567

package asn3_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/***
 * GraphLoader is a utility class with one primary function for loading a graph from a text file.
 * <p>
 * Note: This class is tightly coupled and intended for a very specific format of the input file where the first line
 * contains an integer indicating the number of vertices in the graph and each of the remaining lines contains three
 * integers representing an edge: the source vertex, the target vertex, and the weight of the edge.
 */
public final class GraphLoader {

  public static WeightedGraph loadUndirectedWeightedGraphMaven(String resourcePath)
      throws IOException {
    InputStream inputStream = GraphLoader.class.getResourceAsStream(resourcePath);
    if (inputStream == null) {
      throw new IOException("Resource not found: " + resourcePath);
    }
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line = reader.readLine(); // First line contains the number of vertices
      if (line == null || line.trim().isEmpty()) {
        throw new IOException("The file is empty or does not start with the number of vertices.");
      }
      int n = Integer.parseInt(line.trim()); // Convert to integer, trimming whitespace
      WeightedGraph graph = new WeightedGraph(n);

      // Read the rest of the lines, representing edges
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) continue; // Skip empty lines or lines with whitespace
        String[] parts = line.trim().split("\\s+"); // Split by one or more whitespaces
        int source = Integer.parseInt(parts[0]);
        int target = Integer.parseInt(parts[1]);
        int weight = Integer.parseInt(parts[2]);

        graph.addWeightedEdge(source, target, weight);
      }
      return graph;
    }
  }

  public static WeightedGraph loadUndirectedWeightedGraphFromStdin() throws IOException {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt(); // First input is the number of vertices

    WeightedGraph graph = new WeightedGraph(n);

    while (scanner.hasNextInt()) {
      int source = scanner.nextInt();
      int target = scanner.nextInt();
      int weight = scanner.nextInt();

      graph.addWeightedEdge(source, target, weight);
    }
    scanner.close();
    return graph;
  }
}
