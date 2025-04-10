import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int current = Integer.parseInt(st.nextToken());

            while (!deq.isEmpty() && deq.peekLast().value > current)
                deq.pollLast();

            deq.add(new Node(i, current));
            
            if (deq.peekFirst().idx < (i - L + 1))
                deq.pollFirst();

            sb.append(deq.peekFirst().value).append(" ");
        }

        System.out.println(sb);
    }
}

class Node {
    int idx, value;

    public Node(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}