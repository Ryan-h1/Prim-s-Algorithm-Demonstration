// Ryan Hecht
// 251220567

package asn3_code;

/***
 * This Heap is a unique implementation of a min heap used in Prim's algorithm that allows the algorithm to achieve
 * O((|V| + |E|) log |V|) time complexity.
 * <p>
 * Note: This class is tightly coupled and should not be considered a general-purpose heap implementation, despite its
 * name.
 */
public class Heap {
  private final int[] keys;
  private final int[] indexHeap;
  private final int[] positions;
  private int size;

  public Heap(int[] initialKeys) {
    // The input array is assumed to be 1-based, with index 0 not used.
    this.size = initialKeys.length - 1;
    this.keys = new int[initialKeys.length];
    this.indexHeap = new int[initialKeys.length];
    this.positions = new int[initialKeys.length];

    for (int i = 1; i <= size; ++i) {
      keys[i] = Integer.MAX_VALUE;
      indexHeap[i] = i;
      positions[i] = i;
    }

    for (int i = size / 2; i >= 1; --i) {
      minHeapify(i);
    }
  }

  public boolean inHeap(int id) {
    if (id < 1 || id > size) {
      return false;
    }
    return positions[id] != 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int minKey() {
    return keys[indexHeap[1]];
  }

  public int minId() {
    return indexHeap[1];
  }

  public int key(int id) {
    return keys[id];
  }

  public void deleteMin() {
    if (isEmpty()) {
      throw new IllegalStateException("Heap is empty");
    }

    exchange(1, size);
    positions[indexHeap[size]] = 0; // Mark as removed from heap
    size--;

    if (size != 0) {
      minHeapify(1);
    }
  }

  public void decreaseKey(int id, int newKey)
      throws IndexOutOfBoundsException, IllegalStateException {
    if (id < 1 || id > keys.length - 1) {
      throw new IndexOutOfBoundsException("Invalid index to decrease key");
    }
    if (positions[id] == 0) {
      throw new IllegalStateException("Vertex not in heap");
    }

    if (newKey >= keys[id]) {
      return; // If the new key is not smaller, do nothing. This is not an error.
    }

    keys[id] = newKey;
    minHeapifyUp(positions[id]);
  }

  private void minHeapify(int i) {
    while (true) {
      int left = 2 * i;
      int right = 2 * i + 1;
      int smallest = i;

      if (left <= size && keys[indexHeap[left]] < keys[indexHeap[i]]) {
        smallest = left;
      }

      if (right <= size && keys[indexHeap[right]] < keys[indexHeap[smallest]]) {
        smallest = right;
      }

      if (smallest != i) {
        exchange(i, smallest);
      } else {
        break;
      }
    }
  }

  private void minHeapifyUp(int i) {
    while (i > 1 && keys[indexHeap[i]] < keys[indexHeap[parent(i)]]) {
      exchange(i, parent(i));
      i = parent(i);
    }
  }

  private void exchange(int i, int j) {
    // Swap positions in indexHeap
    int temp = indexHeap[i];
    indexHeap[i] = indexHeap[j];
    indexHeap[j] = temp;

    // Update positions
    positions[indexHeap[i]] = i;
    positions[indexHeap[j]] = j;
  }

  private int parent(int i) {
    return i / 2;
  }
}
