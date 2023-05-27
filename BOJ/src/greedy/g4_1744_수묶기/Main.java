package greedy.g4_1744_수묶기;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            if (temp > 0)
                pos.add(temp);
            else
                neg.add(temp);
        }

        neg.sort(Comparator.naturalOrder());
        for (int i = 0; i < neg.size() - 1; i += 2) {
            ans += neg.get(i) * neg.get(i + 1);
        }
        if (neg.size() % 2 == 1) {
            ans += neg.get(neg.size() - 1);
        }

        pos.sort(Comparator.reverseOrder());
        for (int i = 0; i < pos.size() - 1; i += 2) {
            if (pos.get(i) == 1 || pos.get(i + 1) == 1) {
                ans += pos.get(i) + pos.get(i + 1);
                continue;
            }
            ans += pos.get(i) * pos.get(i + 1);
        }
        if (pos.size() % 2 == 1) {
            ans += pos.get(pos.size() - 1);
        }
        System.out.println(ans);
    }
}
