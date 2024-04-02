package data_structures;

import java.util.Arrays;

public class SuffixArray {
  static class Suffix implements Comparable<Suffix> {
    int index;
    String suffix;

    public Suffix(int index, String suffix) {
      this.index = index;
      this.suffix = suffix;
    }

    public int compareTo(Suffix other) {
      return this.suffix.compareTo(other.suffix);
    }
  }

  public static int[] buildSuffixArray(String text) {
    int n = text.length();
    Suffix[] suffixes = new Suffix[n];

    for (int i = 0; i < n; i++) {
      suffixes[i] = new Suffix(i, text.substring(i));
    }

    Arrays.sort(suffixes);

    int[] suffixArray = new int[n];
    for (int i = 0; i < n; i++) {
      suffixArray[i] = suffixes[i].index;
    }

    return suffixArray;
  }

  public static void main(String[] args) {
    String text = "hippityhoppityboobob";
    int[] suffixArray = buildSuffixArray(text);

    System.out.println("Suffix Array: " + Arrays.toString(suffixArray));
  }
}
