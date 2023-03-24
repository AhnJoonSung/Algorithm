package stack.s2_1874_스택수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            Stack<Integer> stack = new Stack<>();
            int num = 1;
            while (n-- > 0) {
                int input = Integer.parseInt(br.readLine());
                while (num <= input) {
                    stack.push(num);
                    sb.append("+\n");
                    num++;
                }
                if (stack.peek() == input) {
                    stack.pop();
                    sb.append("-\n");
                }
                else {
                    System.out.print("NO\n");
                    return ;
                }
            }
            System.out.print(sb);
        }
    }
}
