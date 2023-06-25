package silver.s2_1927_최소힙;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Heap minHeap = new Heap();
        while (n-- > 0) {
            int x = sc.nextInt();
            if (x == 0)
                System.out.println(minHeap.getMin());
            else
                minHeap.add(x);
        }
    }
}

class Heap {
    private ArrayList<Integer> heap = new ArrayList<>();

    public void add(int value) {
        int idx = heap.size();
        heap.add(value);
        while (heap.get(getParent(idx)) > value) {
            swap(getParent(idx), idx);
            idx = getParent(idx);
            if (idx == 0)
                break;
        }
    }

    public int getMin() {
        if (heap.size() < 1)
            return 0;
        int min = heap.remove(0);
        if (heap.size() < 1)
            return min;

        heap.add(0, heap.remove(heap.size() - 1));
        int idx = 0;
        while (true) {
            if (getLeftChild(idx) >= heap.size())
                break ;

            int target;
            if (getRightChild(idx) >= heap.size())
                target = getLeftChild(idx);
            else
                target = heap.get(getLeftChild(idx)) < heap.get(getRightChild(idx)) ? getLeftChild(idx) : getRightChild(idx);
            if (heap.get(target) < heap.get(idx)) {
                swap(target, idx);
                idx = target;
            }
            else
                break;
        }
        return min;
    }

    private void swap(int idx1, int idx2) {
        int temp = heap.get(idx1);
        heap.set(idx1, heap.get(idx2));
        heap.set(idx2, temp);
    }

    public int getParent(int idx) {
        return (idx - 1) / 2;
    }

    public int getLeftChild(int idx) {
        return idx * 2 + 1;
    }

    public int getRightChild(int idx) {
        return idx * 2 + 2;
    }
}