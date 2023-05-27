package greedy.s2_1541_잃어버린괄호;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] inputs = sc.nextLine().split("[-]");

        int ans = 0;
        for (int i = 0; i < inputs.length; i++) {
            String[] temp = inputs[i].split("[+]");
            int unit = 0;
            for (String s : temp) {
                unit += Integer.parseInt(s);
            }

            if (i == 0) {
                ans += unit;
                continue;
            }
            ans -= unit;
        }
        System.out.println(ans);
    }
}
