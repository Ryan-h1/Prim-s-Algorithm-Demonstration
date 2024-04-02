// Ryan Hecht
// 251220567

package asn3;

public record WeightedEdge<T>(T source, T target, int weight) {

  @Override
  public String toString() {
    return "(" + source + ", " + target + ")" + " : " + weight;
  }
}
