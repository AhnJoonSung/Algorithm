package gold.g5_12865_평범한배낭;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Thing[] things = new Thing[n];
        for (int i = 0; i < n; i++) {
            things[i] = new Thing(sc.nextInt(), sc.nextInt());
        }
    }
}

class Thing {
    int w, v;

    public Thing(int w, int v) {
        this.w = w;
        this.v = v;
    }
}