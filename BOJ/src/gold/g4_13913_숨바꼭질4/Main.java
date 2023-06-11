package gold.g4_13913_숨바꼭질4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static final int MAX = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        boolean[] visited = new boolean[MAX + 1];
        int[] prePos = new int[MAX + 1];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(n, 0));
        visited[n] = true;

        while (!q.isEmpty()) {
            Pos pre = q.poll();
            if (pre.idx == k) {
                System.out.println(pre.time);
                break;
            }
            for (int pos : new int[]{pre.idx + 1, pre.idx - 1, 2 * pre.idx}) {
                if (pos < 0 || pos > MAX || visited[pos])
                    continue;
                q.add(new Pos(pos, pre.time + 1));
                visited[pos] = true;
                prePos[pos] = pre.idx;
            }
        }

        LinkedList<Integer> route = new LinkedList<>();
        while (k != n) {
            route.addFirst(k);
            k = prePos[k];
        }
        route.addFirst(k);

        for(int pos : route) {
            System.out.print(pos + " ");
        }
    }
}

class Pos {
    int idx, time;

    public Pos(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }
}
