package asn3;

public class IndexHeap {
  private final int[] keys; // Store the key value for each element's id
  private final int[] indexHeap; // Heap of indices into keys array
  private final int[] positions; // Positions of ids in the heap for fast lookup
  private int size; // Number of elements in the heap

  public IndexHeap(int[] keys) {
    this.size = keys.length;
    this.keys = new int[size];
    System.arraycopy(keys, 0, this.keys, 0, size);

    this.indexHeap = new int[size];
    this.positions = new int[size];

    for (int i = 0; i < size; ++i) {
      indexHeap[i] = i;
      positions[i] = i;
    }

    for (int i = (size / 2) - 1; i >= 0; --i) {
      minHeapify(i);
    }
  }

  public boolean inHeap(int id) {
    return positions[id] < size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int minKey() {
    return keys[indexHeap[0]];
  }

  public int minId() {
    return indexHeap[0];
  }

  public int key(int id) {
    return keys[id];
  }

  public void deleteMin() {
    if (isEmpty()) {
      throw new IllegalStateException("Heap is empty");
    }
    // Move the last item in the heap to the top, then heapify down from the root
    exchange(0, size - 1);
    size--;
    minHeapify(0);
  }

  public void decreaseKey(int id, int newKey) {
    if (keys[id] <= newKey) {
      throw new IllegalArgumentException("New key is not smaller than the current key");
    }
    keys[id] = newKey;
    minHeapifyUp(positions[id]);
  }

  private void minHeapify(int i) {
    while (true) {
      int left = (i * 2) + 1;
      int right = (i * 2) + 2;
      int smallest = i;

      if (left < size && keys[indexHeap[left]] < keys[indexHeap[smallest]]) {
        smallest = left;
      }

      if (right < size && keys[indexHeap[right]] < keys[indexHeap[smallest]]) {
        smallest = right;
      }

      if (smallest != i) {
        exchange(i, smallest);
        i = smallest;
      } else {
        break;
      }
    }
  }

  private void minHeapifyUp(int i) {
    while (i > 0 && keys[indexHeap[i]] < keys[indexHeap[parent(i)]]) {
      exchange(i, parent(i));
      i = parent(i);
    }
  }

  private void exchange(int i, int j) {
    int tempIndex = indexHeap[i];
    indexHeap[i] = indexHeap[j];
    indexHeap[j] = tempIndex;

    positions[indexHeap[i]] = i;
    positions[indexHeap[j]] = j;
  }

  private int parent(int i) {
    return (i - 1) / 2;
  }
}
