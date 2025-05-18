import java.util.*;

public class Main {
    static int N;
    static int[][] classroom;
    static Map<Integer, Set<Integer>> likeMap = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        classroom = new int[N][N];
        int totalStudents = N * N;

        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < totalStudents; i++) {
            int student = sc.nextInt();
            order.add(student);
            Set<Integer> likes = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                likes.add(sc.nextInt());
            }
            likeMap.put(student, likes);
        }

        for (int student : order) {
            placeStudent(student);
        }

        System.out.println(calculateSatisfaction());
    }

    static void placeStudent(int student) {
        int maxLike = -1;
        int maxEmpty = -1;
        int selectedI = -1;
        int selectedJ = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] != 0) continue;

                int likeCount = 0;
                int emptyCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    if (classroom[nx][ny] == 0) {
                        emptyCount++;
                    } else if (likeMap.get(student).contains(classroom[nx][ny])) {
                        likeCount++;
                    }
                }

                if (likeCount > maxLike ||
                        (likeCount == maxLike && emptyCount > maxEmpty) ||
                        (likeCount == maxLike && emptyCount == maxEmpty && (i < selectedI || (i == selectedI && j < selectedJ)))) {
                    maxLike = likeCount;
                    maxEmpty = emptyCount;
                    selectedI = i;
                    selectedJ = j;
                }
            }
        }

        classroom[selectedI][selectedJ] = student;
    }

    static int calculateSatisfaction() {
        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = classroom[i][j];
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (likeMap.get(student).contains(classroom[nx][ny])) {
                        count++;
                    }
                }

                if (count > 0) {
                    total += Math.pow(10, count - 1);
                }
            }
        }

        return total;
    }
}
