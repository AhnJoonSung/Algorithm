package silver.s1_2504_괄호의값;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Stack<Character> stack = new Stack<>();
        int ans = 0;
        int mul = 1;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                mul *= 2;
                stack.push(c);
            }
            else if (c == '[') {
                mul *= 3;
                stack.push(c);
            }
            else if (c == ')') {
                if (!stack.isEmpty() && stack.pop() == '(') {
                    if (input.charAt(i - 1) == '(')
                        ans += mul;
                    mul /= 2;
                }
                else {
                    error();
                    return ;
                }
            }
            else if (c == ']') {
                if (!stack.isEmpty() && stack.pop() == '[') {
                    if (input.charAt(i - 1) == '[')
                        ans += mul;
                    mul /= 3;
                }
                else {
                    error();
                    return ;
                }
            }
        }
        if (stack.isEmpty())
            System.out.println(ans);
        else
            error();
    }

    public static void error() {
        System.out.println("0");
    }
}
