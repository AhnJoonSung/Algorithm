package linked_list.s2_5397_키로거;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader (System.in))) {
            int tc = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < tc; t++) {
                String input = br.readLine();
                for (char c : getPassword(input))
                    sb.append(c);
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }

    public static LinkedList<Character> getPassword(String input) {
        LinkedList<Character> password = new LinkedList<>();
        int cursor = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '<')
                cursor = cursor == 0 ? 0 : cursor - 1;
            else if (c == '>')
                cursor = cursor == password.size() ? cursor : cursor + 1;
            else if (c == '-') {
                if (password.size() > 0 && cursor != 0) {
                    password.remove(cursor - 1);
                    cursor--;
                }
            }
            else {
                password.add(cursor, c);
                cursor++;
            }
        }
        return (password);
    }
}
