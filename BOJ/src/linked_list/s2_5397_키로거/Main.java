package linked_list.s2_5397_키로거;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader (System.in))) {
            int tc = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < tc; t++) {
                String input = br.readLine();
                sb.append(getPassword(input)).append("\n");
            }
            System.out.print(sb);
        }
    }

    public static StringBuilder getPassword(String input) {
        int cursor = 0;
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            switch (c) {
                case '<':
                    cursor = cursor == 0 ? 0 : cursor - 1;
                    break;
                case '>':
                    cursor = cursor == password.length() ? cursor : cursor + 1;
                    break;
                case '-':
                    if (password.length() > 0 && cursor != 0) {
                        password.deleteCharAt(cursor - 1);
                        cursor--;
                    }
                    break;
                default :
                    password.append(c);
                    cursor++;
            }
        }
        return (password);
    }
}
