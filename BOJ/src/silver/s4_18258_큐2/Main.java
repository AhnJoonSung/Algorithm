package silver.s4_18258_큐2;

import java.util.*;
import java.io.*;

public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Q queue = new Q();
        StringBuilder sb = new StringBuilder();
        while (n-- >0) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            switch (command) {
                case "push" :
                    queue.push(Integer.parseInt(st.nextToken()));
                    break;                    
                case "pop" :
                    sb.append(queue.pop()).append("\n");
                    break;
                case "size" :
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty" :
                    sb.append(queue.empty()).append("\n");
                    break;
                case "front" :
                    sb.append(queue.front()).append("\n");
                    break;
                case "back" :
                    sb.append(queue.back()).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }
}

class Q {
    LinkedList<Integer> queue;

    public Q() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        if (!queue.isEmpty()) {
            return (queue.poll());
        }
        return (-1);
    }

    public int size() {
        return (queue.size());
    }

    public int empty() {
        if (queue.isEmpty())
            return (1);
        return (0);
    }

    public int front() {
        if (!queue.isEmpty())
            return (queue.peek());
        return (-1);
    }

    public int back() {
        if (!queue.isEmpty())
            return (queue.getLast());
        return (-1);
    }
}