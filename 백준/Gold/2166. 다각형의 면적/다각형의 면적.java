import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        Coor[] coors = new Coor[n];

        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();

            coors[i] = new Coor(x, y);
        }

        double extent = 0;

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            extent += coors[i].mul(coors[next]);
        }

        extent /= 2;
        System.out.printf("%.1f%n", Math.abs(extent));

        sc.close();
    }
}

class Coor {
    long x, y;

    public Coor(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long mul(Coor other) {
        return x*other.y - y*other.x;
    }
}