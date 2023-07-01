package silver.s5_11723_집합;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int s = 0;
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            String cmd = sc.next();

            if (cmd.equals("add")) {
                int x = sc.nextInt() - 1;
                s |= (1 << x);
            }
            else if (cmd.equals("remove")) {
                int x = sc.nextInt() - 1;
                s &= ~(1 << x);
            }
            else if (cmd.equals("check")) {
                int x = sc.nextInt() - 1;
                int temp = s & (1 << x);
                temp >>= x;
                sb.append(temp).append("\n");
            }
            else if (cmd.equals("toggle")) {
                int x = sc.nextInt() - 1;
                s ^= (1 << x);
            }
            else if (cmd.equals("all"))
                s = (int)Math.pow(2, 20) - 1;
            else if (cmd.equals("empty"))
                s = 0;
        }
        System.out.print(sb);
    }
}