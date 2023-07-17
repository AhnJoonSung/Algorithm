package silver.s1_1991_트리순회;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Node root;
        for (int i = 0; i < n; i++) {
            
        }
    }
}

class Node {
    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    Node left, right;

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
