package gold.g5_14891_톱니바퀴;

import java.util.Scanner;

public class Main {
    private static final int GEAR_CNT = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Gear[] gears = new Gear[GEAR_CNT + 1];
        for (int i = 1; i <= GEAR_CNT; i++) {
            gears[i] = new Gear(sc.nextLine());
        }

        int k = sc.nextInt();
        while (k-- > 0) {
            int targetNum = sc.nextInt();
            int direction = sc.nextInt();

            checkLeft(gears, targetNum, direction);
            checkRight(gears, targetNum, direction);
            gears[targetNum].rotate(direction);
        }

        System.out.println(calculateScore(gears));
    }

    public static void checkLeft(Gear[] gears, int targetNum, int direction) {
        if (targetNum == 1) {
            return;
        }

        Gear target = gears[targetNum];
        Gear newTarget = gears[targetNum - 1];

        if (target.getLeft() != newTarget.getRight()) {
            checkLeft(gears, targetNum - 1, -1 * direction);
            newTarget.rotate(-1 * direction);
        }
    }

    public static void checkRight(Gear[] gears, int targetNum, int direction) {
        if (targetNum == GEAR_CNT) {
            return;
        }

        Gear target = gears[targetNum];
        Gear newTarget = gears[targetNum + 1];

        if (target.getRight() != newTarget.getLeft()) {
            checkRight(gears, targetNum + 1, -1 * direction);
            newTarget.rotate(-1 * direction);
        }
    }

    public static int calculateScore(Gear[] gears) {
        int ans = 0;

        for (int i = 1; i <= GEAR_CNT; i++) {
            ans += gears[i].getTop() * Math.pow(2, i - 1);
        }
        return ans;
    }
}

class Gear {
    private final int TOOTH = 8;

    private int top, left, right;
    private final String gear;

    public Gear(String gear) {
        this.gear = gear;
        top = 0;
        left = 6;
        right = 2;
    }

    public void rotate(int direction) {
        top = ((top - direction) + TOOTH) % TOOTH;
        right = ((right - direction) + TOOTH) % TOOTH;
        left = ((left - direction) + TOOTH) % TOOTH;
    }

    public char getLeft() {
        return gear.charAt(left);
    }

    public char getRight() {
        return gear.charAt(right);
    }

    public int getTop() {
        return gear.charAt(top) - '0';
    }
}
