package silver.s4_1158_요세푸스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader (System.in))) {
            String[] inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            int k = Integer.parseInt(inputs[1]);

            LinkedList<Integer> circle = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                circle.add(i);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("<");
            int num;
            int size = n;
            int idx = 0;
            while (size > 1) {
                idx = (idx + k - 1) % size;
                num = circle.remove(idx);
                sb.append(num).append(", ");
                size--;
            }
            sb.append(circle.remove()).append(">");
            System.out.print(sb);
        }
    }
}