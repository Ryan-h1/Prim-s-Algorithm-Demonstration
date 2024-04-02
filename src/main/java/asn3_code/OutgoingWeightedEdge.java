// Ryan Hecht
// 251220567

package asn3_code;

public record OutgoingWeightedEdge<T>(T target, int weight) {

  @Override
  public String toString() {
    return "(" + target + ", " + weight + ")";
  }
}
