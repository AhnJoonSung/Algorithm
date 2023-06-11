package silver.s4_1822_차집합;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int na = sc.nextInt();
        int nb = sc.nextInt();

        int[] a = new int[na];
        int[] b = new int[nb];

        for (int i = 0; i < na; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < nb; i++) {
            b[i] = sc.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);

        List<Integer> ansList = new ArrayList<>();
        for (int num : a) {
            if (!binarySearch(b, num))
                ansList.add(num);
        }
        System.out.println(ansList.size());
        for (int num : ansList) {
            System.out.print(num + " ");
        }
    }

    public static boolean binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (num < arr[mid]) {
                right = mid - 1;
            } else if (num > arr[mid]) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
