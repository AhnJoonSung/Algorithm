import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] durabilities = new int[2*n];
        for (int i = 0; i < 2*n; i++) {
            durabilities[i] = sc.nextInt();
        }

        Factory factory = new Factory(n, durabilities);

        int depth = 0;
        while (factory.cnt < k) {
            factory.roll();
            factory.move();
            factory.putRobot();
            depth++;
        }

        System.out.println(depth);
        sc.close();
    }
}

class Factory {
    int putIdx, outIdx, len;
    int cnt;
    Node[] belt;
    int[] durabilities;

    public Factory(int n, int[] durabilities) {
        len = 2*n;
        belt = new Node[len];
        for (int i = 0; i < len; i++)
            belt[i] = new Node(durabilities[i], null);
        this.durabilities = durabilities;
        putIdx = 0;
        outIdx = n-1;
    }

    public void putRobot() {
        if (belt[putIdx].durability != 0) {
            belt[putIdx].robot = new Robot();
            if (--belt[putIdx].durability == 0)
                cnt++;
        }
    }

    public void roll() {
        putIdx = getValidIdx(putIdx - 1);
        outIdx = getValidIdx(outIdx - 1);

        getOff();
    }

    public void move() {
        for (int i = getValidIdx(outIdx - 1); i != getValidIdx(putIdx-1); i = getValidIdx(i-1)) {
            int next = getValidIdx(i + 1);

            if (belt[next].robot != null)
                continue;
            if (belt[next].durability < 1)
                continue;
            if (belt[i].robot == null)
                continue;

            belt[next].robot = belt[i].robot;
            belt[i].robot = null;

            if (--belt[next].durability == 0)
                cnt++;
        }

        getOff();
    }

    private void getOff() {
        belt[outIdx].robot = null;
    }

    private int getValidIdx(int idx) {
        if (idx < 0)
            return len + idx;
        if (idx >= len)
            return idx % len;
        return idx;
    }
}

class Node {
    int durability;
    Robot robot;

    public Node(int durability, Robot robot) {
        this.durability = durability;
        this.robot = robot;
    }
}

class Robot {
    public Robot() {

    }
}
