import java.util.*;

public class Main {
    static final Map<Character, Integer> priority = Map.of(
        '*', 2,
        '/', 2,
        '+', 1,
        '-', 1,
        '(', 0,
        ')', 0
        );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        String operation = sc.nextLine();

        for (char c: operation.toCharArray()) {
            if ('A' <= c && c <= 'Z')
                sb.append(c);
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty()) {
                    char value = stack.pop();
                    if (value == '(')
                        break;
                    sb.append(value);
                }
            } else {
                while (!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        System.out.println(sb);
    }
}