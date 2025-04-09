import java.util.*;

public class Main {
    static final int QUOT = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        System.out.println(fibonacci(n));
    }

    static long fibonacci(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        // fib(n) = {{1, 1}, {1, 0}}^(n-1)[0][0];
        long[][] result = power(new long[][]{{1, 1}, {1, 0}}, n - 1);
        return result[0][0];
    }

    static long[][] power(long[][] base, long exp) {
        long[][] result = {{1, 0}, {0, 1}};
        while (exp > 0) {
            if (exp % 2 == 1)
                result = multiply(result, base);
            base = multiply(base, base);
            exp /= 2;
        }
        return result;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % QUOT;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % QUOT;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % QUOT;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % QUOT;
        return result;
    }
}