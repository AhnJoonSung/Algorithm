package dynamic_programming.s3_1003_피보나치함수;

import java.util.Scanner;

public class Main {
    public static final int MAX = 41;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            int[] zero = new int[MAX];
            int[] one = new int[MAX];
            zero[0] = 1;
            one[1] = 1;
            for (int i = 2; i < MAX; i++) {
                zero[i] = zero[i - 1] + zero[i - 2];
                one[i] = one[i - 1] + one[i - 2];
            }
            System.out.println(zero[n] + " " + one[n]);
        }
    }
}
