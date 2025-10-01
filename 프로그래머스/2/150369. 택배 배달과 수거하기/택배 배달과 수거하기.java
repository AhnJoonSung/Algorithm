import java.util.*;

class Solution {
    /*
    n개의 집
    배달하고 빈상자 수거
    집은 1거리 간격으로
    최대 버퍼 cap개
    각 집마다: 택배수, 수거 수
    -> 모든 배달을 마칠 최소 이동 거리(복귀까지)
    최대로 싣고 제일 먼집부터 역순으로 선착순,
    돌아올 때도 먼집부터 수거
    */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long moves = 0;
        
        Deque<Integer> food, box;
        food = new ArrayDeque<>();
        box = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            int dist = i + 1;
            
            int foods = deliveries[i];
            int boxes = pickups[i];
            
            while (foods-- > 0) {
                food.offerLast(dist);
            }
            while (boxes-- > 0) {
                box.offerLast(dist);
            }
        }
        
        while (!food.isEmpty() || !box.isEmpty()) {
            int max = -1;
            
            int temp = cap;
            while (temp-- > 0 && !food.isEmpty()) {
                max = Math.max(max, food.peekLast());
                food.pollLast();
            }
            temp = cap;
            while (temp-- > 0 && !box.isEmpty()) {
                max = Math.max(max, box.peekLast());
                box.pollLast();
            }
            
            moves += (max * 2);
        }
        
        return moves;
    }
}