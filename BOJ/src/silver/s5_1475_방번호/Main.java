package silver.s5_1475_방번호;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        int[] cnt = new int[10];

        for (int i = 0; i < n.length(); i++) {
            int digit = n.charAt(i) - '0';
            if (digit == 6)
                cnt[9]++;
            else
                cnt[digit]++;
        }
        int max = (cnt[9] + 1) / 2;
        for (int i = 0; i < cnt.length - 1; i++) {
            if (cnt[i] > max)
                max = cnt[i];
        }
        System.out.println(max);
    }
}
