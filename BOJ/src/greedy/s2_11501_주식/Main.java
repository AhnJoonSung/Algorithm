package greedy.s2_11501_주식;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            Stack<Integer> stack = new Stack<>();

            long ans = 0;
            for (int i = 0; i < n; i++) {
                stack.add(sc.nextInt());
            }
            while (!stack.isEmpty()) {
                int temp = stack.pop();
                while (!stack.isEmpty() && stack.peek() <= temp) {
                    ans += temp - stack.pop();
                }
            }
            System.out.println(ans);
        }
    }
}
