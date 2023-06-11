package gold.g5_18869_멀티버스2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);

        int[][] universes = new int[m][n];

        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                universes[i][j] = Integer.parseInt(inputs[j]);
            }
            universes[i] = compress(universes[i]);
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (Arrays.equals(universes[i], universes[j]))
                    cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static int[] compress(int[] universe) {
        int[] result = new int[n];
        int[] temp = Arrays.copyOf(universe, universe.length);
        Arrays.sort(temp);

        for (int i = 0; i < n; i++) {
            result[i] = getIdx(temp, universe[i]);
        }
        return result;
    }

    public static int getIdx(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
                result = mid;
            }
        }
        return result;
    }
}
