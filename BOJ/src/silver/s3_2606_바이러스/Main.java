package silver.s3_2606_바이러스;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = sc.nextInt();

        Set<Integer> ans = new HashSet<>();
        while (cnt-- > 0) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            if (from == 1 || to == 1) {
                ans.add(from);
                ans.add(to);
            }
            else if (ans.contains(from) || ans.contains(to)) {
                ans.add(from);
                ans.add(to);
            }
        }
        ans.remove(1);
        System.out.println(ans.size());
    }
}
