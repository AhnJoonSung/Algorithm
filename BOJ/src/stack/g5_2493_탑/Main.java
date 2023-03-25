package stack.g5_2493_탑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.LinkedList;

public class Main {
    static final int MAX = 100000000;
    public static void main(String[] args) throws IOException{
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");
            LinkedList<String> result = new LinkedList<>();

            Stack<Building> buildings = new Stack<>();
            buildings.push(new Building(0, MAX + 1));
            for (int i = 1; i <= n; i++) {
                int height = Integer.parseInt(inputs[i - 1]);
                
                while (buildings.peek().height < height) {
                    buildings.pop();
                }
                result.add(Integer.toString(buildings.peek().index));
                buildings.push(new Building(i, height));
            }
            System.out.println(String.join(" ", result));
        }
    }
}

class Building {
    int index;
    int height;

    public Building(int index, int height) {
        this.index = index;
        this.height = height;
    }
}
