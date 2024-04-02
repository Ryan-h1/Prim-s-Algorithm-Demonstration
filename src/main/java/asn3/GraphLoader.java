package asn3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class GraphLoader {

  public static WeightedGraph loadUndirectedWeightedGraph(String resourcePath) throws IOException {
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
}
