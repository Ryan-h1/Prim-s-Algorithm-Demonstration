package asn3;

public class WeightedEdgeSingleTarget<T> {
  public final T target;
  public final int weight;

  WeightedEdgeSingleTarget(T target, int weight) {
    this.target = target;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return "(" + target + ", " + weight + ")";
  }
}
