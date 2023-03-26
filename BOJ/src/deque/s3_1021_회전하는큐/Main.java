package deque.s3_1021_회전하는큐;

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        LinkedList<Integer> targets = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            targets.add(sc.nextInt());
        }
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            deque.addLast(i);
        }
        int ans = 0;
        for (int target : targets) {
            int index = deque.indexOf(target);
            int size = deque.size();
            if (index < size - index) {
                ans += index;
                while (index-- > 0) {
                    deque.add(deque.removeFirst());
                }
            } else {
                ans += size - index;
                while (size - index++ > 0) {
                    deque.addFirst(deque.removeLast());
                }
            }
            deque.removeFirst();
        }
        System.out.println(ans);
    }
}
