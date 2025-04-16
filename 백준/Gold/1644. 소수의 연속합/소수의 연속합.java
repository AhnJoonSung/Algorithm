import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(solve(n));

        sc.close();
    }

    static int solve(int n) {
        if (n==1) return 0;
        if (n==2 || n==3) return 1;

        List<Integer> primes = getPrimes(n);
        int size = primes.size();

        long[] sum = new long[size];
        sum[0] = primes.get(0);
        for (int i = 1; i < size; i++)
            sum[i] = sum[i-1] + primes.get(i);

        int cnt = 0;
        for (int len = 1; len <= size; len++) {
            for (int i = len-1; i < size; i++) {
                long subSum = sum[i];
                if (i >= len)
                    subSum -= sum[i-len];

                if (subSum == n)
                    cnt++;
                else if (subSum > n)
                    break;
            }
        }

        return cnt;
    }

    static List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n < 2) return primes;

        boolean[] isNotPrime = new boolean[n+1];
        for (int i = 2; i*i <= n; i++) {
            if (isNotPrime[i]) continue;

            for (int j = i*i; j <= n; j+=i) {
                isNotPrime[j] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i])
                primes.add(i);
        }

        return primes;
    }
}