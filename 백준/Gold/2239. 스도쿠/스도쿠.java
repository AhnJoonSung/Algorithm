import java.util.*;

public class Main {
    static char[][] puzzle;

    static Set<Character>[] rows;
    static Set<Character>[] cols;
    static Set<Character>[][] boxs;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 9;
        puzzle = new char[n][n];

        rows = new Set[n];
        cols = new Set[n];
        for (int i = 0; i < n; i++) {
            puzzle[i] = sc.nextLine().toCharArray();
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
        }

        boxs = new Set[n/3][n/3];
        for (int i = 0; i < n/3; i++) {
            for (int j = 0; j < n/3; j++)
                boxs[i][j] = new HashSet<>();
        }

        List<Node> emptyNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = puzzle[i][j];
                if (c == '0') {
                    emptyNodes.add(new Node(i, j));
                    continue;
                }

                rows[i].add(c);
                cols[j].add(c);
                boxs[i/3][j/3].add(c);
            }
        }
        
        backtrack(0, emptyNodes);

        sc.close();
    }

    static boolean backtrack(int depth, List<Node> emptyNodes) {
        if (depth == emptyNodes.size()) {
            printPuzzle();
            return true;
        }

        Node node = emptyNodes.get(depth);
        int i = node.i;
        int j = node.j;

        for (char c = '1'; c <= '9'; c++) {
            if (rows[i].contains(c)) continue;
            if (cols[j].contains(c)) continue;
            if (boxs[i/3][j/3].contains(c)) continue;

            rows[i].add(c);
            cols[j].add(c);
            boxs[i/3][j/3].add(c);
            
            puzzle[i][j] = c;

            if (backtrack(depth+1, emptyNodes))
                return true;

            rows[i].remove(c);
            cols[j].remove(c);
            boxs[i/3][j/3].remove(c);
        }
        return false;
    }

    static void printPuzzle() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(puzzle[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}

class Node {
    int i, j;

    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}