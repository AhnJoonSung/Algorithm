package silver.s1_13335_트럭;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int w = sc.nextInt();
        int L = sc.nextInt();

        Queue<Integer> trucks = new LinkedList<>();
        while (n-- > 0)
            trucks.add(sc.nextInt());

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++)
            bridge.add(0);
        int time = 0;
        int avail = L;

        while (!trucks.isEmpty()) {
            time++;
            avail += bridge.poll();
            if (avail >= trucks.peek()) {
                int truck = trucks.poll();
                bridge.add(truck);
                avail -= truck;
            } else
                bridge.add(0);
        }
        time += w;
        System.out.println(time);
    }
}
