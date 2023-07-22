package silver.s1_1991_트리순회;

import java.util.Scanner;

public class Main {
    private static final char NULL = '.';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Node[] tree = new Node[n];
        for (int i = 0; i < n; i++)
            tree[i] = new Node((char) ('A' + i));

        for (int i = 0; i < n; i++) {
            char parent = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            if (left != NULL)
                tree[parent - 'A'].setLeft(tree[left - 'A']);
            if (right != NULL)
                tree[parent - 'A'].setRight(tree[right - 'A']);
        }

        preorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        postorder(tree[0]);
        System.out.println();
    }
    static void preorder(Node node) {
        System.out.print(node.getData());
        if (node.getLeft() != null)
            preorder(node.getLeft());
        if (node.getRight() != null)
            preorder(node.getRight());
    }

    static void inorder(Node node) {
        if (node.getLeft() != null) inorder(node.getLeft());
        System.out.print(node.getData());
        if (node.getRight() != null) inorder(node.getRight());
    }

    static void postorder(Node node) {
        if (node.getLeft() != null) postorder(node.getLeft());
        if (node.getRight() != null) postorder(node.getRight());
        System.out.print(node.getData());
    }
}

class Node {
    char data;

    public char getData() {
        return data;
    }

    public Node(char data) {
        this.data = data;
    }

    Node left, right;

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
