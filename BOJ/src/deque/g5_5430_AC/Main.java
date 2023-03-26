package deque.g5_5430_AC;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            String func = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();

            input = input.replaceAll("^\\[|]$", "");
            String[] arr = input.split(",");
            List<String> list = Arrays.asList(arr);
            LinkedList<String> arr_list = new LinkedList<>(list);

            boolean is_error = false;
            boolean reverse = false;

            for (int i = 0; i < func.length(); i++) {
                char cmd = func.charAt(i);

                if (cmd == 'R') {
                    reverse = !reverse;
                }
                else if (cmd == 'D') {
                    if (n == 0 || arr_list.isEmpty()) {
                        is_error = true;
                        break;
                    }
                    if (!reverse) {
                        arr_list.removeFirst();
                    } else {
                        arr_list.removeLast();
                    }
                }
            }

            if (is_error)
                System.out.println("error");
            else if (reverse) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                int size = arr_list.size();
                for (int i = 0; i < size; i++) {
                    sb.append(arr_list.pollLast());
                    if (i != size - 1)
                        sb.append(",");
                }
                sb.append("]");
                System.out.println(sb);
            } else {
                String s = arr_list.toString().replaceAll(" ", "");
                System.out.println(s);
            }
        }
    }
}
