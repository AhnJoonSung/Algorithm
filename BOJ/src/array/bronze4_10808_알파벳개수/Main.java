package array.bronze4_10808_알파벳개수;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int[] alpha_cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alpha_cnt[s.charAt(i) - 'a']++;
        }

        StringJoiner joiner = new StringJoiner(" ");
        for (int cnt : alpha_cnt) {
            joiner.add(Integer.toString(cnt));
        }
        System.out.println(joiner);
    }
}
