package silver.s4_1620_나는야포켓몬마스터이다솜;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        String[] pocketArr = new String[n + 1];
        Map<String, Integer> pocketName = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            pocketArr[i] = sc.next();
            pocketName.put(pocketArr[i], i);
        }

        while (m-- > 0) {
            String input = sc.next();
            if (isNum(input))
                System.out.println(pocketArr[Integer.parseInt(input)]);
            else
                System.out.println(pocketName.get(input));
        }
    }

    private static boolean isNum(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
