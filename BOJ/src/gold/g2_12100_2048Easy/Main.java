package gold.g2_12100_2048Easy;

import java.util.Scanner;

public class Main {
    static final int LEFT = 4;
    static final int DOWN = 1;
    static final int RIGHT = 2;
    static final int UP = 3;
    static final int[] directions = {LEFT, DOWN, RIGHT, UP};
    static final int EMPTY = 0;

    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(recursive(map, 0));
    }

    public static int recursive(int[][] map, int idx) {
        if (idx == 5) {
            return (getMax(map));
        }
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int temp = recursive(press(map, directions[i]), idx + 1);
            if (temp > max)
                max = temp;
        }
        return (max);
    }

    public static int getMax(int[][] map) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > max)
                    max = map[i][j];
            }
        }
        return (max);
    }

    public static int[][] press(int[][] map, int dir) {
        for (int i = 0; i < dir; i++) {
            map = rotate(map);
        }
        for (int row = 0; row < n; row++) {
            int[] temp = new int[n];
            int temp_idx = 0;
            for (int i = 0; i < n; i++) {
                if (map[row][i] == EMPTY)
                    continue;
                if(temp[temp_idx] == EMPTY) {
                    temp[temp_idx] = map[row][i];
                }
                else if (temp[temp_idx] == map[row][i]) {
                    temp[temp_idx] *= 2;
                    temp_idx++;
                }
                else {
                    temp_idx++;
                    temp[temp_idx] = map[row][i];
                }
            }
            for (int i = 0; i < n; i++) {
                map[row][i] = temp[i];
            }
        }
        for (int i = 0; i < (8 - dir); i++) {
            map = rotate(map);
        }
        return (map);
    }

    public static int[][] rotate(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] temp = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++){
                temp[i][j] = arr[(row - 1) - j][i];
            }
        }
        return (temp);
    }
}