import java.util.*;

class Main {
    static final int MAX = 100_000;
    static final int MOD = 10_000;
    static final String PRINT = "print", MALLOC="malloc", FREE="free";
    static final int NULL = 0;

    static TreeSet<Block> freeList;
    static Map<String, Integer> vars;
    static Map<Integer, Block> allocated;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        vars = new HashMap<>();
        allocated = new HashMap<>();
        freeList = new TreeSet<>();

        freeList.add(new Block(1, MAX));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();

            if (input.startsWith(PRINT)) {
                String varName = input.substring(6, 10);
                sb.append(print(varName)).append("\n");
            } else if (input.startsWith(FREE)) {
                String varName = input.substring(5, 9);
                free(varName);
            } else {
                String varName = input.substring(0, 4);
                int size = Integer.parseInt(input.substring(12, input.length() - 2));
                int address = malloc(size);
                vars.put(varName, address);
            }
        }

        System.out.print(sb);
        sc.close();
    }

    static int malloc(int size) {
        if (size > MAX) return NULL;

        Block block = getFreeBlock(size);
        if (block == null)
            return NULL;

        if (block.size > size) {
            Block remain = new Block(block.start + size, block.size - size);
            freeList.add(remain);
            block.resize(size);
        }

        allocated.put(block.start, block);
        return block.start;
    }

    static Integer print(String varName) {
        return vars.getOrDefault(varName, 0);
    }

    static void free(String varName) {
        int address = vars.getOrDefault(varName, NULL);
        if (address == NULL) return;

        Block block = allocated.remove(address);
        vars.put(varName, NULL);
        coalescing(block);
    }

    static void coalescing(Block block) {
        Block lower = freeList.lower(block);
        Block higher = freeList.higher(block);

        if (lower != null && lower.end + 1 == block.start) {
            freeList.remove(lower);
            block = new Block(lower.start, lower.size + block.size);
        }

        if (higher != null && block.end + 1 == higher.start) {
            freeList.remove(higher);
            block = new Block(block.start, block.size + higher.size);
        }

        freeList.add(block);
    }

    static Block getFreeBlock(int size) {
        Block block = null;
        for (Block freeBlock: freeList) {
            if (freeBlock.size >= size) {
                block = freeBlock;
                break;
            }
        }

        if (block != null)
            freeList.remove(block);
        return block;
    }
}

class Block implements Comparable<Block> {
    int start, end, size;

    public Block(int start, int size) {
        this.start = start;
        this.end = start + size - 1;
        this.size = size;
    }

    public void resize(int newSize) {
        this.size = newSize;
        this.end = this.start + newSize - 1;
    }

    @Override
    public int compareTo(Block other) {
        return this.start - other.start;
    }
}