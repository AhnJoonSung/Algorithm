package silver.s4_3986_좋은단어;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int ans = 0;
        while (n-- > 0) {
            String word = sc.nextLine();
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (stack.isEmpty()) {
                    stack.push(c);
                    continue;
                }

                if (stack.peek() != c) {
                    stack.push(c);
                }
                else {
                    stack.pop();
                }
            }
            if (stack.isEmpty())
                ans++;
        }
        System.out.println(ans);
    }
}
