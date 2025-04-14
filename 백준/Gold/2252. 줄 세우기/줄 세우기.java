import java.util.*;

public class Main {
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        int[] inDegree = new int[n+1];
        Set<Integer>[] sets = new Set[n+1];
        for (int i = 1; i <= n; i++)
            sets[i] = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            sets[a].add(b);
            inDegree[b]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(inDegree[i] == 0)
                q.add(i);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int target: sets[node]) {
                inDegree[target]--;
                if (inDegree[target] == 0)
                    q.add(target);
            }
            sb.append(node).append(" ");
        }

        System.out.println(sb);
        sc.close();
    }
}