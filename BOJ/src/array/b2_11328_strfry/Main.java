package array.b2_11328_strfry;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    static String POSSIBLE = "Possible";
    static String IMPOSSIBLE = "Impossible";

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            String s1, s2;
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                s1 = input[0];
                s2 = input[1];
                setMap(s1, hashMap, 1);
                setMap(s2, hashMap, -1);
                if (hashMap.isEmpty())
                    System.out.println(POSSIBLE);
                else
                    System.out.println(IMPOSSIBLE);
                hashMap.clear();
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setMap(String s, HashMap<Character, Integer> hashMap, int cal) {
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            int newValue = hashMap.getOrDefault(c, 0) + cal;
            if (newValue == 0)
                hashMap.remove(c);
            else
                hashMap.put(c, newValue);
        }
    }
}
