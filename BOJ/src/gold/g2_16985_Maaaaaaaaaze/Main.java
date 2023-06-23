package gold.g2_16985_Maaaaaaaaaze;

import java.util.Scanner;

public class Main {
    public static final int SIZE = 5;
    public static final int WALL = 0;
    public static final int EMPTY = 1;
    public static final int IMPOSSIBLE = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][][] maze = new int[SIZE][SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    maze[i][j][k] = sc.nextInt();
                }
            }
        }


    }

    public static void permutation(int[][][] maze, int i, int ans) {
        if (i == SIZE)
            return ;


        for (int cnt = 0; cnt < 4; cnt++) {
            ans = Math.min(ans, bfs(maze, new Node(0,0,0)));
            maze[i] = rotate(maze[i]);
        }
    }

    public static int bfs(int[][][] maze, Node start) {
        int distance = 0;
        return 0;
    }

    public static int[][] rotate(int[][] board) {
        int[][] rotated = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rotated[i][j] = board[SIZE - 1 - j][i];
            }
        }
        return rotated;
    }
}

class Node {
    int i, j, k;

    public Node(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }
}
