import java.util.*;
class Solution {
    static Map<Integer, List<Integer>> graph;
    
    static int eightCnt, stickCnt, donutCnt;
    
    public int[] solution(int[][] edges) {
        
        graph = new HashMap<>();
        
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            List<Integer> nodes = graph.getOrDefault(from, new ArrayList<>());
            nodes.add(to);
            graph.put(from, nodes);
        }
        
        // 나가는게 2개 이상이고 들어오는건 없으면 추가된 애
        Map<Integer, Integer> inOut = new HashMap<>();
        for (int key: graph.keySet()) {
            // 나가는 수는 빼줌
            List<Integer> out = graph.get(key);
            inOut.put(key, inOut.getOrDefault(key, 0) - out.size());
            
            // 들어오는 건 더해줌
            for (int node: out) {
                inOut.put(node, inOut.getOrDefault(node, 0) + 1);
            }
        }
        
        int added = -1;
        for (int key: graph.keySet()) {
            if (inOut.get(key) < -1) {
                added = key;
                break;
            }
        }
             
        
        int[] answer = new int[4];
        answer[0] = added;
        
        for (int i = 1; i < 4; i++)
            answer[i] = 0;
        
        List<Integer> startNodes = graph.get(added);
        for (int startNode: startNodes) {
            travle(startNode);
        }
        
        answer[1] = donutCnt;
        answer[2] = stickCnt;
        answer[3] = eightCnt;
        
        return answer;
    }
    
    private void travle(int startNode) {
        // 가다가 끝나면 막대
        // 특정 노드에서 2개가 나가면 8자
        // 아니면 도넛

        Set<Integer> visited = new HashSet<>();
        int current = startNode;

        while (true) {
            visited.add(current);
            List<Integer> next = graph.get(current);

            if (next == null || next.isEmpty()) {
                stickCnt++;
                return;
            } else if (next.size() > 1) {
                eightCnt++;
                return;
            }

            current = next.get(0);
            if (visited.contains(current)) {
                donutCnt++;
                return;
            }
        }
    }
}