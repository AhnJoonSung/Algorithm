package silver.s4_10828_스택;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                String command = input[0];
                int x = 0;
                if (input.length == 2)
                    x = Integer.parseInt(input[1]);

                if (command.compareTo("push") == 0)
                    stack.push(x);
                else if (command.compareTo("pop") == 0) {
                    if (stack.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println((stack.pop()));
                }
                else if (command.compareTo("size") == 0) {
                    System.out.println(stack.size());
                }
                else if (command.compareTo("empty") == 0) {
                    if (stack.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                }
                else if (command.compareTo("top") == 0) {
                    if (stack.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(stack.peek());
                }
            }
        }
    }
}
