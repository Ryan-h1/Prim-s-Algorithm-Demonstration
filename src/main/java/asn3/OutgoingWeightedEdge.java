// Ryan Hecht
// 251220567

package asn3;

public record OutgoingWeightedEdge<T>(T target, int weight) {

  @Override
  public String toString() {
    return "(" + target + ", " + weight + ")";
  }
}
