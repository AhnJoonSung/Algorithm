package array.b2_13300_방배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int YEAR = 6;
    static int SEX = 2;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int[][] students = new int[YEAR + 1][SEX];
            String[] input = br.readLine().split("\\s+");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);

            int s, y;
            for (int i = 0; i < n; i++) {
                input = br.readLine().split("\\s+");
                s = Integer.parseInt(input[0]);
                y = Integer.parseInt(input[1]);
                students[y][s]++;
            }
            br.close();

            int ans = 0;
            for (int[] year : students) {
                for (int cnt : year) {
                    ans += (cnt + k - 1) / k;
                }
            }
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
