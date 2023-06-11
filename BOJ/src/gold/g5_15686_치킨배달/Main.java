package gold.g5_15686_치킨배달;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final int HOUSE = 1;
    public static final int CHICKEN = 2;

    public static int N, M;
    public static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ArrayList<Coord> houses = new ArrayList<>();
        ArrayList<Coord> chickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = sc.nextInt();
                if (temp == HOUSE)
                    houses.add(new Coord(i, j));
                else if (temp == CHICKEN) {
                    chickens.add(new Coord(i, j));
                }
            }
        }

        ans = 2 * N * N * N;
        int[] selected = new int[M];
        comb(chickens, houses, selected, 0);

        System.out.println(ans);
    }

    public static void comb(ArrayList<Coord> chickens, ArrayList<Coord> houses, int[] selected, int idx) {
        if (idx == M) {
            int temp = 0;
            for (Coord house : houses) {
                temp += get_chicken_dist(chickens, selected, house);
            }
            ans = Math.min(ans, temp);
            return ;
        }

        int start;
        if (idx == 0)
            start = 0;
        else
            start = selected[idx - 1] + 1;
        int end = chickens.size() + idx - M + 1;
        for (int i = start; i < end; i++) {
            selected[idx] = i;
            comb(chickens, houses, selected, idx + 1);
        }
    }

    public static int get_chicken_dist(ArrayList<Coord> chickens, int[] selected, Coord house) {
        int dist = 2 * N;
        
        for (int idx : selected) {
            dist = Math.min(dist, get_dist(chickens.get(idx), house));
        }
        return (dist);
    }
    
    public static int get_dist(Coord x, Coord y) {
        return (Math.abs(x.i - y.i) + Math.abs(x.j - y.j));
    }
}

class Coord {
    int i, j;

    public Coord(int i, int j) {
        this.i = i;
        this.j = j;
    }
}