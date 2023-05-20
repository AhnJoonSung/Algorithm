package stack.p5_1725_히스토그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long max = 0;
        Stack<Rectangle> stack = new Stack<>();

        while (n-- > 0) {
            long h = Long.parseLong(br.readLine());
            long w = 0;

            while (!stack.isEmpty() && h <= stack.peek().height) {
                w += stack.peek().width;
                max = Math.max(max, stack.peek().height * w);
                stack.pop();
            }
            w++;
            stack.push(new Rectangle(h, w));
        }
        long w = 0;
        while (!stack.isEmpty()) {
            w += stack.peek().width;
            max = Math.max(max, w * stack.peek().height);
            stack.pop();
        }
        System.out.println(max);
    }

}

class Rectangle {
    long height, width;

    public Rectangle(long height, long width) {
        this.height = height;
        this.width = width;
    }
}
