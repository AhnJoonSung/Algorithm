package stack.g4_17298_오큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node[] data = new Node[n];

        for (int i = 0; i < n; i++) {
            data[i] = new Node(i, sc.nextInt());
        }

        Stack<Node> stack = new Stack<>();
        int[] ans = new int[n];
        ans[n - 1] = -1;

        for (int i = 0; i < n - 1; i++) {
            int target = data[i + 1].value;

            if (data[i].value < target) {
                ans[i] = target;
                while (!stack.isEmpty() && (stack.peek().value < target)) {
                    ans[stack.pop().idx] = target;
                }
            }
            else {
                stack.push(new Node(i, data[i].value));
            }
        }
        while (!stack.isEmpty()) {
            ans[stack.pop().idx] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}

class Node {
    int idx, value;

    public Node(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}
