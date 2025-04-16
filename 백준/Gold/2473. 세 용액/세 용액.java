import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        Arrays.sort(arr);

        int[] result = solve(arr, n);
        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);

        sc.close();
    }

    static int[] solve(int[] arr, int n) {
        long min = Long.MAX_VALUE;
        int[] answer = new int[3];
        for (int i = 0; i < n-2; i++) {
            int fix = arr[i];
            int left = i+1;
            int right = n-1;

            while (left < right) {
                long sum = (long)fix + arr[left] + arr[right];
                
                if (Math.abs(sum) < min) {
                    answer[0] = fix;
                    answer[1] = arr[left];
                    answer[2] = arr[right];
                    min = Math.abs(sum);
                }

                if (sum > 0)
                    right--;
                else 
                    left++;
            }
        }

        return answer;
    }
}