package silver.s1_1629_곱셈;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        System.out.println(power(a, b, c));
    }

    public static long power(long a, long b, long c) {
        if (b == 1)
            return a % c;

        long half = power(a, b/2, c) % c;

        if (b % 2 == 0)
            return (half * half) % c;
        return (((half * half) % c) * a) % c;
    }
}
