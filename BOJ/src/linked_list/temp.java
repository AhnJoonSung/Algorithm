package linked_list;

import java.util.LinkedList;
import java.util.Scanner;

public class temp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = sc.nextInt();
        LinkedList<Character> list = new LinkedList<>();
        int cursor = str.length();
        char option;
        char c; //option P로 받는 새 문자

        for(int i = 0; i < str.length(); i++){
            list.add(str.charAt(i));
        }
        int list_size = list.size();
        for(int i = 0; i < n; i++){
            option = sc.next().charAt(0);
            if(option == 'P'){
                c = sc.next().charAt(0);
                list.add(cursor, c);
                cursor++;
                list_size++;
            }
            else if(option == 'L' && cursor > 0){
                cursor--;
            }
            else if(option == 'D' && cursor < list_size){
                cursor++;
            }
            else if(option == 'B' && cursor > 0){
                cursor--;
                list.remove(cursor);
                list_size--;
            }
        }

        for (Character s: list) {
            System.out.print(s);
        }
    }
}