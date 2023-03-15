package array.s5_1475_방번호;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        int[] set = new int[10];

        for (int i = 0; i < n.length(); i++) {
            int digit = n.charAt(i) - '0';
            if (digit == 6)
                set[9]++;
            else
                set[digit]++;
        }
        int max = (set[9] + 1) / 2;
        for (int i = 0; i < set.length - 1; i++) {
            if (set[i] > max)
                max = set[i];
        }
        System.out.println(max);
    }
}
