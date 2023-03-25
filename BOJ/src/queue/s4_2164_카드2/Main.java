package queue.s4_2164_카드2;

import java.util.*;
import java.io.*;

public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<String> card = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            card.add(Integer.toString(i));
        }
        while (n-- > 1) {
            card.poll();
            card.add(card.poll());
        }
        System.out.println(card.peek());
    }
}