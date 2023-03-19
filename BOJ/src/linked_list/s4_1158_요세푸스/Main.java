package linked_list.s4_1158_요세푸스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader (System.in))) {
            String[] inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            int k = Integer.parseInt(inputs[1]);

            Queue<Integer> circle = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                circle.add(i);
            }

            int size = n;
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            while (size > 1) {
                for (int i = 0; i < k - 1; i++) {
                    circle.add(circle.poll());
                }
                sb.append(circle.poll()).append(", ");
                size--;
            }
            sb.append(circle.poll()).append(">");
            System.out.print(sb);
        }
    }
}