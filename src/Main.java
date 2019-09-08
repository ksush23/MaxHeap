import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String result = "";

        int n = scanner.nextInt();

        MaxHeap maxHeap = new MaxHeap(n);

        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();

            if (line.charAt(0) == 'I') {
                line = line.substring(7);
                int add = Integer.parseInt(line);

                maxHeap.insert(add);
            }

            if (line.charAt(0) == 'E') {
                result += maxHeap.extractMax() + "\n";
            }
        }
        System.out.println(result);
    }
}

class MaxHeap {
    private int[] Heap;
    private int size;
    private int maxsize;

    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity.
    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    private void swap(int fpos, int spos) {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // This function assumes that the left and
    // right subtrees are already heapified, we only need
    // to fix the root.
    private void maxHeapify(int pos) {

        int j = 0;
        while (2 * pos <= size){
            if (Heap[2 * pos] > Heap[pos]){
               j = 2 * pos;
            }

            if (2 * pos + 1 <= size){
                if (Heap[2 * pos + 1] > Heap[pos] && Heap[j] < Heap[2 * pos + 1]){
                    j = 2 * pos + 1;
                }
            }

            if (pos == j)
                return;

            if (pos != j){
                swap(pos, j);
                pos = j;
            }
        }
    }

    // Inserts a new element to max heap
    public void insert(int element) {
        Heap[++size] = element;

        // Traverse up and fix violated property
        int current = size;
        while (current > 1 && Heap[current] > Heap[current / 2]){
            swap(current, current / 2);
            current = current / 2;
        }
    }

    // Remove an element from max heap
    public int extractMax() {
        int popped = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return popped;
    }
}