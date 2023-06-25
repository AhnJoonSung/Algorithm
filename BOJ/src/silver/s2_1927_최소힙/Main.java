package silver.s2_1927_최소힙;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        while (n-- > 0) {
            int x = sc.nextInt();

            if (x > 0)
                minHeap.add(x);
            else {
                if (minHeap.isEmpty())
                    System.out.println(0);
                else
                    System.out.println(minHeap.poll());
            }
        }
    }
}