package backtracking.g3_1941_소문난칠공주;

import java.util.*;

public class Main {
    public static final char DOY = 'Y';
    public static final char SOM = 'S';
    public static final int ALL = 25;
    public static final int SELECT = 7;
    public static final int MAX = 3;
    public static final int[] di = {-1, 1, 0, 0};
    public static final int[] dj = {0, 0, -1, 1};
    public static final int row = 5;
    public static final int col = 5;

    public static int ans = 0;
    public static ArrayList<Node> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < row; i++) {
            String input = sc.nextLine();
            for (int j = 0; j < col; j++) {
                students.add(new Node(i, j, input.charAt(j)));
            }
        }
        comb(new int[SELECT], 0, 0);

        System.out.println(ans);
    }

    public static void comb(int[] selected, int idx, int doy) {
        if (doy > MAX)
            return ;
        if (idx == SELECT) {
            if (check(selected))
                ans++;
            return ;
        }

        int start, end;
        if (idx == 0)
            start = 0;
        else
            start = selected[idx - 1];
        end = ALL - SELECT + idx + 1;
        for (int i = start; i < end; i++) {
            selected[idx] = i;
            if (students.get(i).group == DOY)
                comb(selected, idx + 1, doy + 1);
            else
                comb(selected, idx + 1, doy);
        }
    }

    public static boolean check(int[] selected) {
        char[][] temp = new char[row][col];
        Queue<Node> q = new LinkedList<>();
        for (char[] t : temp) {
            Arrays.fill(t, 'X');
        }
        for (int i : selected) {
            Node cur = students.get(i);
            temp[cur.i][cur.j] = 'O';
        }
        int cnt = 0;
        boolean[][] visited = new boolean[row][col];
        Node node = students.get(selected[0]);
        q.add(node);
        cnt++;
        visited[node.i][node.j] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (i < 0 || i >= row || j < 0 || j >= col)
                    continue;
                if (visited[i][j] || temp[i][j] != 'O')
                    continue;
                cnt++;
                q.add(new Node(i, j, 'O'));
                visited[i][j] = true;
            }
        }
        if (cnt != SELECT)
            return (false);
        return true;
    }
}

class Node {
    int i, j;
    char group;

    public Node(int i, int j, char group) {
        this.i = i;
        this.j = j;
        this.group = group;
    }
}
