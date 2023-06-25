package silver.s1_1074_Z;

import java.util.Scanner;

public class Main {
    public static int SEQ_NUM = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        int size = (int)Math.pow(2, n);

        find(size, r, c);
        System.out.println(SEQ_NUM);
    }

    private static void find(int size, int r, int c) {
        if (size == 1)
            return ;

        int cnt = size * size;
        int newSize = size / 2;

        if (r < size / 2 && c < size / 2) {
            find(newSize, r, c);
        } else if (r < size / 2) {
            SEQ_NUM += (cnt / 4);
            find(newSize, r, c - newSize);
        } else if (c < size / 2) {
            SEQ_NUM += (cnt / 4) * 2;
            find(newSize, r - newSize, c);
        } else {
            SEQ_NUM += (cnt / 4) * 3;
            find(newSize, r - newSize, c - newSize);
        }
    }
}
