package silver.s1_1931_회의실배정;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            meetings[i][0] = sc.nextInt();
            meetings[i][1] = sc.nextInt();
        }

        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[1]));

        int ans = 0;
        int time = 0;
        for (int i = 0; i < n; i++) {
            if (meetings[i][0] < time) {
                continue;
            }
            time = meetings[i][1];
            ans++;
        }

        System.out.println(ans);
    }
}
