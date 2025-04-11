import java.util.*;

public class Main {
    static final int POWER = -10;
    static final char STRONG_PLUS = '+' + POWER, STRONG_MINUS = '-' + POWER, STRONG_MUL = '*' + POWER;
    static int n, answer = Integer.MIN_VALUE;
    static char[] elements;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = Integer.parseInt(sc.nextLine());
        elements = sc.nextLine().toCharArray();

        backtrack(0, n / 2, 0);

        System.out.println(answer);
        sc.close();
    }

    static void backtrack(int depth, int max, int selected) {
        if (depth == max) {
            String postfix = getPostfix(selected);
            answer = Math.max(answer, calculate(postfix));
            return;
        }

        if (depth != 0 && ((selected & (1 << depth -1)) != 0))
            backtrack(depth + 1, max, selected);
        else {
            backtrack(depth + 1, max, selected);
            backtrack(depth + 1, max, selected | (1 << depth));
        }
    }

    static int calculate(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (char c: postfix.toCharArray()) {
            if (isDigit(c))
                stack.push(c - '0');
            else {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(cal(a, c, b));
            }
        }

        return stack.peek();
    }

    static String getPostfix(int selected) {
        String postfix = "";

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = elements[i];
            if (isDigit(c)) {
                postfix += c;
                continue;
            }
            if ((selected & (1 << i/2)) != 0) {
                stack.push((char)(c + POWER));
                continue;
            }
            while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
                postfix += stack.pop();
            }
            stack.push(c);
        }
        
        while (!stack.isEmpty())
            postfix += stack.pop();

        return postfix;
    }

    static int getPriority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*') return 2;
        if (op == STRONG_MINUS || op == STRONG_PLUS) return 3;
        if (op == STRONG_MUL) return 3;

        return 0;
    }

    static int cal(int a, char op, int b) {
        if (op == '+' || op == STRONG_PLUS) return a + b;
        if (op == '-' || op == STRONG_MINUS) return a - b;
        if (op == '*' || op == STRONG_MUL) return a * b;
        
        return 0;
    }

    static boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }
}

