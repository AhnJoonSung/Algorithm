import java.util.*;

class Solution {
    /*
    표 편집
    표: 50 x 50
    셀: 초기에 비어 있음, 문자열 값을 가짐, 다른 셀과 병합 가능
    -> 자료구조로 표현하기 까다로움(2차원 배열에서 각 셀마다 1 ~ 2500 넘버링 + (넘버: 문자열 값) Map 으로 관리
    or Cell 객체로 다루기. 효율성 따져보고 결정
    Cell 객체의 2차원 배열로 다루면 index 접근하기 편할듯? 병합은 같은 객체를 각 칸마다 넣기
    -> 한 칸의 값만 바꿔도 같은 객체니까 같이 바뀜
    
    UPDATE: (value: Cell) Map을 관리
    MERGE: Cell에서 (r,c)값을 여러 개 가지고 있을 수 있게
    
    */
    static Cell[][] table;
    static Map<String, Set<Cell>> valueMap;
    static int n;
    
    public String[] solution(String[] commands) {
        n = 50;
        table = new Cell[n+1][n+1];
        valueMap = new HashMap<>();
        
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                Cell cell = new Cell(new Point(r, c));
                table[r][c] = cell;
            }
        }
        
        List<String> results = new ArrayList<>();
        
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            
            String[] args = command.split(" ");
            String ins = args[0];
            
            if ("UPDATE".equals(ins)) {
                if (args.length == 4) {
                    int r = Integer.parseInt(args[1]);
                    int c = Integer.parseInt(args[2]);
                    String value = args[3];
                    
                    update(r, c, value);
                } else if (args.length == 3) {
                    String value1 = args[1];
                    String value2 = args[2];
                    
                    update(value1, value2);
                }
                
            } else if ("MERGE".equals(ins)) {
                
                int r1 = Integer.parseInt(args[1]);
                int c1 = Integer.parseInt(args[2]);
                int r2 = Integer.parseInt(args[3]);
                int c2 = Integer.parseInt(args[4]);
                
                merge(r1, c1, r2, c2);
                
            } else if ("UNMERGE".equals(ins)) {
                int r = Integer.parseInt(args[1]);
                int c = Integer.parseInt(args[2]);
                
                unmerge(r, c);
            } else if ("PRINT".equals(ins)) {
                int r = Integer.parseInt(args[1]);
                int c = Integer.parseInt(args[2]);
                
                String result = print(r, c);
                results.add(result);
            } else {
                System.out.println("wtf");
            }
        }
        
        String[] answer = new String[results.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = results.get(i);
        }
        
        return answer;
    }
    
    
    /*
    (1,2 1,3 1,4) -> unmerge
    1,2: group
    1,3: group
    1,4: group
    2,2: hansik
    
    아니 입력값대로 따라갔는데 내 결과가 맞는데? 문제를 잘못 이해했나보네
    */
    public void update(int r, int c, String value) {
        Cell cell = table[r][c];
        String oldValue = cell.value;
        
        if (!oldValue.isEmpty() && valueMap.containsKey(oldValue)) {
            valueMap.get(oldValue).remove(cell);
        }
        
        cell.update(value);
        
        // System.out.println("update result " + r + "," + c + " : " + table[r][c].value);
        
        Set<Cell> cells = valueMap.getOrDefault(value, new HashSet<>());
        cells.add(cell);
        valueMap.put(value, cells);
    }
    
    public void update(String value1, String value2) {
        Set<Cell> cells = valueMap.get(value1);
        if (cells == null) {
            return;
        }
        
        for (Cell cell: cells) {
            cell.update(value2);
        }
        
        valueMap.put(value1, new HashSet<>());
        Set<Cell> og = valueMap.getOrDefault(value2, new HashSet<>());
        cells.addAll(og);
        valueMap.put(value2, cells);
    }
    
    public void merge(int r1, int c1, int r2, int c2) {
        Cell cell1 = table[r1][c1];
        Cell cell2 = table[r2][c2];
        
        cell1.merge(cell2);
        
        Set<Point> points = cell2.points;
        for (Point point: points) {
            table[point.r][point.c] = cell1;
        }
    }
    
    public void unmerge(int r, int c) {
        Cell cell = table[r][c];
        
        Set<Point> points = cell.points;
        String value = cell.value;
        
        for (Point point: points) {
            Cell newCell;
            
            if (point.r == r && point.c == c) {
                newCell = new Cell(point, value);
                Set<Cell> newCells = new HashSet<>();
                newCells.add(newCell);
                valueMap.put(value, newCells);
            } else {
                newCell = new Cell(point);
            }
            
            table[point.r][point.c] = newCell;
        }
    }
    
    public String print(int r, int c) {
        Cell cell = table[r][c];
        return cell.print();
    }
}

class Cell {
    Set<Point> points;
    String value;
    
    public Cell(Point point) {
        points = new HashSet<>();
        points.add(point);
        
        value = "";
    }
    
    public Cell(Point point, String value) {
        points = new HashSet<>();
        points.add(point);
        
        this.value = value;
    }
    
    public void update(String newValue) {
        this.value = newValue;
    }
    
    public void merge(Cell other) {
        if (this == other)
            return;
        
        this.points.addAll(other.points);
        
        // 둘 다 값을 가지고 있을 경우, 주체의 값으로
        if (!this.value.isEmpty() && !other.value.isEmpty()) {
            return;
        }
        
        // other가 값이 있는 경우
        if (!other.value.isEmpty()) {
            this.value = other.value;
        }
    }
    
    public String print() {
        if (value.isEmpty())
            return "EMPTY";
        else
            return value;
    }
}

class Point{
    int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        
        if (!(other instanceof Point)) return false;
        
        Point p = (Point)other;
        if (r == p.r && c == p.c)
            return true;
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}