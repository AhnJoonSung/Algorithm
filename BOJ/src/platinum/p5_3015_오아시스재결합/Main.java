package platinum.p5_3015_오아시스재결합;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Stack<Node> stack = new Stack<>();

        long ans = 0;
        while (n-- > 0) {
            int height = sc.nextInt();
            int cnt = 1;
            //지금 보고 있는 애보다 작거나 같은 애들을 pop (= 스택을 내림차순으로 관리)
            while (!stack.isEmpty() && stack.peek().height <= height) {
                //겹쳐진 cnt만큼 ans에 더하고,
                ans += stack.peek().cnt;

                //만약 높이가 같으면 cnt에다가 더해준다.
                if (stack.peek().height == height)
                    cnt += stack.peek().cnt;

                stack.pop();
            }
            //현재 높이 이하를 다 pop하고 남은 애는 서로 볼 수 있으므로, empty가 아니면 ans++
            if (!stack.isEmpty())
                ans++;

            //현재 보고 있는 애 넣어줌
            stack.push(new Node(height, cnt));
        }
        System.out.println(ans);
    }
}

class Node {
    int height, cnt;

    public Node(int height, int cnt) {
        this.height = height;
        this.cnt = cnt;
    }
}
