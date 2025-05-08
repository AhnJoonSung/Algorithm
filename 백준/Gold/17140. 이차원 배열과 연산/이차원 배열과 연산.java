import java.util.*;
/*  */
public class Main {
    static final int MAX = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int k = sc.nextInt();

        int[][] arr = new int[MAX][MAX];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(calculate(arr, r, c, k));
    }

    static int calculate(int[][] arr, int r, int c, int k) {
        for (int time = 0; time < MAX; time++) {
            if (arr[r][c] == k) return time;

            if (rowCnt(arr) >= colCnt(arr))
                arr = r(arr);
            else
                arr = c(arr);
        }

        return arr[r][c] == k? MAX : -1;
    }

    static int rowCnt(int[][] a) {
        int cnt = 0;
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (a[i][j] != 0) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static int colCnt(int[][] a) {
        int cnt = 0;
        for (int j = 0; j < MAX; j++) {
            for (int i = 0; i < MAX; i++) {
                if (a[i][j] != 0) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static int[][] r(int[][] arr) {
        int[][] newArr = new int[MAX][MAX];

        for (int i = 0; i < MAX; i++) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int j = 0; j < MAX; j++) {
                int num = arr[i][j];
                if (num == 0)
                    continue;

                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }

            List<Integer> sortedNum = new ArrayList<>(counts.keySet());

            sortedNum.sort((a, b) -> {
                int cmp = Integer.compare(counts.get(a),counts.get(b));

                if (cmp == 0) {
                    return Integer.compare(a, b);
                }

                return cmp;
            });


            for (int j = 0; j < sortedNum.size() && j < MAX/2; j++) {
                newArr[i][2*j] = sortedNum.get(j);
                newArr[i][2*j+1] = counts.get(sortedNum.get(j));
            }
        }

        return newArr;
    }

    static int[][] c(int[][] arr) {
        int[][] newArr = new int[MAX][MAX];

        for (int j = 0; j < MAX; j++) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int i = 0; i < MAX; i++) {
                int num = arr[i][j];
                if (num == 0)
                    continue;

                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }

            List<Integer> sortedNum = new ArrayList<>(counts.keySet());

            sortedNum.sort((a, b) -> {
                int cmp = Integer.compare(counts.get(a),counts.get(b));

                if (cmp == 0) {
                    return Integer.compare(a, b);
                }

                return cmp;
            });

            for (int i = 0; i < sortedNum.size() && i < MAX/2; i++) {
                newArr[2*i][j] = sortedNum.get(i);
                newArr[2*i+1][j] = counts.get(sortedNum.get(i));
            }
        }

        return newArr;
    }
}