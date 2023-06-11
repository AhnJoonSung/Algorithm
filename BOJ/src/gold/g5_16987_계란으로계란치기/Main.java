package gold.g5_16987_계란으로계란치기;

import java.util.Scanner;

public class Main {
    public static int n, max;
    public static int[] dura, weight;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dura = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            dura[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }

        max = 0;
        recursive(0, 0);
        System.out.println(max);
    }

    public static void recursive(int idx, int cnt) {
        if (idx == n) {
            return ;
        }
        if (dura[idx] <= 0) {
            recursive(idx + 1, cnt);
            return ;
        }

        for (int i = 0; i < n; i++) {
            if (idx == i || dura[i] <= 0)
                continue;
            dura[idx] -= weight[i];
            dura[i] -= weight[idx];
            int temp = 0;
            if (dura[idx] <= 0)
                temp++;
            if (dura[i] <= 0)
                temp++;
            max = Math.max(max, cnt + temp);
            recursive(idx + 1, cnt + temp);
            dura[idx] += weight[i];
            dura[i] += weight[idx];
        }
    }
}
