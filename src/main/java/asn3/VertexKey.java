package asn3;

class VertexKey implements Comparable<VertexKey> {
  int vertex;
  int key;

  VertexKey(int vertex, int key) {
    this.vertex = vertex;
    this.key = key;
  }

  @Override
  public int compareTo(VertexKey other) {
    return Integer.compare(this.key, other.key);
  }
}
