import java.util.*;

public class Main {
    static int n, m;
    static int answer = Integer.MAX_VALUE, max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        List<Guitar> guitars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            guitars.add(new Guitar(sc.next(), sc.next()));
        }

        for (int i = 1; i <= n; i++) {
            backtrack(0, i, new ArrayList<>(), guitars);
        }

        if (max == 0)
            answer = -1;

        System.out.println(answer);
        sc.close();
    }

    static void backtrack(int start, int selectCnt, List<Guitar> selected, List<Guitar> guitars) {
        if (selected.size() == selectCnt) {
            int cnt = countSongs(selected);
            if (cnt > max) {
                max = cnt;
                answer = selectCnt;
            } else if (cnt == max) {
                answer = Math.min(answer, selectCnt);
            }
                
            return;
        }

        for (int i = start; i < n; i++) {
            selected.add(guitars.get(i));
            backtrack(i + 1, selectCnt, selected, guitars);
            selected.remove(selected.size() - 1);
        }
    }

    static int countSongs(List<Guitar> guitars) {
        Set<Integer> songs = new HashSet<>();

        for (Guitar g: guitars) {
            for (int i = 0; i < m; i++) {
                if (g.songs.charAt(i) == 'Y')
                    songs.add(i);
            }
        }

        return songs.size();
    }
}

class Guitar {
    String name, songs;

    public Guitar(String name, String songs) {
        this.name = name;
        this.songs = songs;
    }
}