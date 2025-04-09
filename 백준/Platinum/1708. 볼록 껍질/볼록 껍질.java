import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        Point[] points = new Point[n];

        long minY = 40_000, minX = 40_000;
        Point minP = null;
        for (int i = 0; i < n; i++) {
            long x = sc.nextInt();
            long y = sc.nextInt();
            points[i] = new Point(x, y);

            if (y < minY || y == minY && x < minX) {
                minY = y;
                minX = x;
                minP = points[i];
            }
        }

        // 가장 좌측하단에 있는 p0를 기준으로 x축 양의 방향과 선분(po-p1)이 이루는 각도가 작은 게 앞에 오도록 정렬
        // 만약 같은 각도면 더 가까운 애가 앞에
        final Point p0 = minP;
        Arrays.sort(points, (p1, p2) -> {
            if (p1 == p0) return -1;
            if (p2 == p0) return 1;

            long ccw = CCW(p0, p1, p2);
            if (ccw > 0) return -1;
            if (ccw < 0) return 1;
            
            long dist = dist(p0, p1) - dist(p0, p2);
            if (dist < 0) return -1;
            if (dist > 0) return 1;
            return 0;
        });

        // 정렬된 상태에서 세 점 씩 가면서 오목하게 되면 안에 있는 점(p2) pop
        // 마지막 점까지 하고 나면 가장 외각 점들만 스택에 남게 됨
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);

        for (int i = 1; i < n; i++) {
            while (stack.size() >= 2) {
                Point p1 = stack.get(stack.size() - 2);
                Point p2 = stack.peek();
                Point p3 = points[i];

                if (CCW(p1, p2, p3) > 0) break;

                stack.pop();
            }
            stack.push(points[i]);
        }

        System.out.println(stack.size());
    }

    static long dist(Point p1, Point p2) {
        long a = (p2.x - p1.x);
        long b = (p2.y - p1.y);

        return a*a + b*b; 
    }

    static long CCW(Point p0, Point p1, Point p2) {
        long[] a = {p1.x - p0.x, p1.y - p0.y};
        long[] b = {p2.x - p0.x, p2.y - p0.y};

        return (a[0] * b[1]) - (a[1] * b[0]); 
    }

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}