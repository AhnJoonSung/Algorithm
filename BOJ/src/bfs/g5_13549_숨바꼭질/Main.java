package bfs.g5_13549_숨바꼭질;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int MAX = 100001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        boolean[] visited = new boolean[MAX];
        visited[n] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.i == k) {
                System.out.println(cur.time);
                return ;
            }
            int i = cur.i * 2;
            while (0 <= i && i < MAX && !visited[i]) {
                q.add(new Node(i, cur.time));
                visited[i] = true;
                i *= 2;
            }
            if (cur.i > 0 && !visited[cur.i - 1]) {
                q.add(new Node(cur.i - 1, cur.time + 1));
                visited[cur.i - 1] = true;
            }
            if (cur.i < MAX - 1 && !visited[cur.i + 1]) {
                q.add(new Node(cur.i + 1, cur.time + 1));
                visited[cur.i + 1] = true;
            }
        }
    }
}

class Node {
    int i, time;

    public Node(int i, int time) {
        this.i = i;
        this.time = time;
    }
}