package array.b2_1919_애너그램만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str1 = br.readLine();
            String str2 = br.readLine();

            int[] str1Char = new int[26];
            int[] str2Char = new int[26];

            setStrChar(str1, str1Char);
            setStrChar(str2, str2Char);

            int cnt = 0;
            for (int i = 0; i < 26; i++) {
                cnt += Math.abs(str1Char[i] - str2Char[i]);
            }

            System.out.println(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setStrChar(String str, int[] strChar) {
        for (int i = 0; i < str.length(); i++) {
            strChar[str.charAt(i) - 'a']++;
        }
    }
}
