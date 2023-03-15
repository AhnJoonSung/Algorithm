package array.b2_11328_strfry;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Main {
    private static final String POSSIBLE = "Possible";
    private static final String IMPOSSIBLE = "Impossible";

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                String str1 = input[0];
                String str2 = input[1];

                if (isPossible(str1, str2))
                    sb.append(POSSIBLE + "\n");
                else
                    sb.append(IMPOSSIBLE + "\n");
            }
            System.out.print(sb);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPossible(String str1, String str2) {
        return (Arrays.equals(getCnt(str1), getCnt(str2)));
    }

    private static int[] getCnt(String str) {
        int[] cnt = new int[26];
        for (int i = 0; i < str.length(); i++) {
            cnt[str.charAt(i) - 'a']++;
        }
        return (cnt);
    }
}
