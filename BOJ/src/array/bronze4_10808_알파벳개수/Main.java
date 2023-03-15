package array.bronze4_10808_알파벳개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String s = br.readLine();
            int[] alpha_cnt = new int[26];
            Arrays.fill(alpha_cnt, 0);

            for (int i = 0; i < s.length(); i++) {
                alpha_cnt[s.charAt(i) - 'a']++;
            }

            StringJoiner joiner = new StringJoiner(" ");
            for (int cnt : alpha_cnt) {
                joiner.add(Integer.toString(cnt));
            }
            System.out.println(joiner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
