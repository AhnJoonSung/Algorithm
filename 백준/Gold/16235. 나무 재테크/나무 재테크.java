import java.util.*;

public class Main {
    static int n;

    static Node[][] map;
    static int[][] amounts;
    static List<Tree> diedTrees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        map = new Node[n+1][n+1];
        amounts = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                amounts[i][j] = sc.nextInt();
                map[i][j] = new Node(i, j, 5);
            }
        }

        while (m-- > 0) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int age = sc.nextInt();

            map[i][j].addTree(new Tree(age, map[i][j]));
        }

        while (k-- > 0) {
            spring();
            summer();
            autumn();
            winter();
        }
        
        System.out.println(getLiveCnt());
        sc.close();
    }

    static int getLiveCnt() {
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cnt += map[i][j].trees.size();
            }
        }

        return cnt;
    }

    static void spring() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                PriorityQueue<Tree> lived = new PriorityQueue<>();
                PriorityQueue<Tree> trees = map[i][j].trees;

                while (!trees.isEmpty() && trees.peek().eat()) {
                    lived.add(trees.poll());
                }

                while (!trees.isEmpty()) {
                    diedTrees.add(trees.poll());
                }

                map[i][j].trees = lived;
            }
        }
    }

    static void summer() {
        for (Tree tree: diedTrees) {
            tree.die();
        }
        diedTrees = new ArrayList<>();
    }

    static void autumn() {
        Node[][] newMap = new Node[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Tree tree: map[i][j].trees) {
                    tree.makeBabies(newMap);
                }
            }
        }

        map = newMap;
    }

    static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j].amount += amounts[i][j];
            }
        }
    }
}

class Tree implements Comparable<Tree> {
    static final int di[] = {-1,-1,-1,0,0,1,1,1};
    static final int dj[] = {-1,0,1,-1,1,-1,0,1};

    int age;
    Node ground;

    public Tree(int age, Node ground) {
        this.age = age;
        this.ground = ground;
    }

    public boolean eat() {
        if (ground.amount < age)
            return false;
            
        ground.amount -= age;
        age++;
        return true;
    }

    public void die() {
        ground.amount += (age / 2);
    }

    public void makeBabies(Node[][] map) {
        if (age % 5 != 0)
            return;

        for (int dir = 0; dir < 8; dir++) {
            int ni = ground.i + di[dir];
            int nj = ground.j + dj[dir];

            if (ni < 1 || nj < 1 || ni >= map.length || nj >= map[0].length)
                continue;

            map[ni][nj].addTree(new Tree(1, map[ni][nj]));
        }
    }

    @Override
    public int compareTo(Tree other) {
        return this.age - other.age;
    }
}

class Node {
    int i, j;
    int amount;

    PriorityQueue<Tree> trees;

    public Node(int i, int j, int amount) {
        this.i = i;
        this.j = j;
        this.amount = amount;
        trees = new PriorityQueue<>();
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }
}