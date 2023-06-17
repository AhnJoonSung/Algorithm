package gold.g4_3190_뱀;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static final int APPLE = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] board = new int[n][n];
        while(k-- > 0)
            board[sc.nextInt() - 1][sc.nextInt() - 1] = APPLE;

        int l = sc.nextInt();
        Queue<Command> commands = new LinkedList<>();
        while (l-- > 0)
            commands.add(new Command(sc.nextInt(), sc.next().charAt(0)));

        int time = 0;
        Snake snake = new Snake(board);
        while (!snake.isEnd()) {
            snake.move();
            time++;
            if (commands.isEmpty())
                continue;
            if (time == commands.peek().time)
                snake.turn(commands.poll().direction);
        }
        System.out.println(time);
    }
}

class Snake {
    private static final int[] di = {0, 1, 0, -1};
    private static final int[] dj = {1, 0, -1, 0};
    private static final int SNAKE = 1;
    private static final int APPLE = 2;
    private static final int EMPTY = 0;

    private final LinkedList<Node> snake = new LinkedList<>();
    private int direction = 0;
    private final int[][] board;
    private boolean isEnd = false;

    public Snake(int[][] board) {
        this.board = board;
        snake.add(new Node(0, 0));
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void move() {
        Node head = snake.getLast();
        int newI = head.i + di[direction];
        int newJ = head.j + dj[direction];

        if (isOut(newI, newJ) || board[newI][newJ] == SNAKE) {
            isEnd = true;
            return ;
        }
        if (board[newI][newJ] != APPLE) {
            Node tail = snake.removeFirst();
            board[tail.i][tail.j] = EMPTY;
        }
        snake.add(new Node(newI, newJ));
        board[newI][newJ] = SNAKE;
    }

    public void turn(char newDir) {
        if (newDir == 'L')
            direction = (direction + 3) % 4;
        else if (newDir == 'D')
            direction = (direction + 1) % 4;
    }

    private boolean isOut(int i, int j) {
        if (i < 0 || i >= board.length)
            return true;
        if (j < 0 || j >= board[i].length)
            return true;
        return false;
    }
}

class Command {
    int time;
    char direction;

    public Command(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }
}

class Node {
    int i, j;

    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}