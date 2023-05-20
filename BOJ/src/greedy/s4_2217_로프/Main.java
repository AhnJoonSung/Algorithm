package greedy.s4_2217_로프;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Integer[] ropes = new Integer[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = sc.nextInt();
        }
        Arrays.sort(ropes, Comparator.reverseOrder());

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, ropes[i] * (i + 1));
        }
        System.out.println(ans);
    }
}
