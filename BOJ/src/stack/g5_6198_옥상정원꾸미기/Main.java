package stack.g5_6198_옥상정원꾸미기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
   public static void main(String[] args) throws IOException{
       try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
           int n = Integer.parseInt(br.readLine());

           Stack<Integer> buildings = new Stack<>();
           long ans = 0;
           for (int i = 0; i < n; i++) {
               int h = Integer.parseInt(br.readLine());
               while (!buildings.isEmpty() && buildings.peek() <= h) {
                   buildings.pop();
               }
               ans += buildings.size();
               buildings.push(h);
           }
           System.out.println(ans);
       }
   } 
}
