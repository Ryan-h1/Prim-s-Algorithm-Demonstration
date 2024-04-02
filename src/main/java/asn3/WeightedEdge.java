package asn3;

public class WeightedEdge<T> {
  public final T source;
  public final T target;
  public final int weight;

  public WeightedEdge(T source, T target, int weight) {
    this.source = source;
    this.target = target;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return "(" + source + ", " + target + ")" + " : " + weight;
  }
}
