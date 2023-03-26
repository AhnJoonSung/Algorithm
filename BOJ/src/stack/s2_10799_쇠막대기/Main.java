package stack.s2_10799_쇠막대기;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Stack<Character> stack = new Stack<>();
        boolean isLaser = false;
        int ans = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                stack.push(c);
                isLaser = true;
            }
            else if (c == ')') {
                stack.pop();
                if (isLaser)
                    ans += stack.size();
                else
                    ans++;
                isLaser = false;
            }
        }
        System.out.println(ans);
    }
}
