import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        
        List<Integer> results = new ArrayList<>();
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = toBinary(numbers[i]);
            
            binary = normalize(binary);
            
            if (check(binary))
                results.add(1);
            else
                results.add(0);
                
        }
        
        int[] answer = new int[results.size()];
        for (int i = 0; i < results.size(); i++)
            answer[i] = results.get(i);
        
        return answer;
    }
    
    private boolean check(String binary) {
        int n = binary.length();
        if (n == 1) return true;
        
        int mid = n/2;
        String left = binary.substring(0, mid);
        String right = binary.substring(mid+1);
        
        if (binary.charAt(mid) == '0') {
            if (left.contains("1") || right.contains("1"))
                return false;
            return true;
        }
        
        return (check(left) && check(right));
    }
    
    private String normalize(String binary) {
        int len = binary.length();
        int maxLength = getMaxLength(len);

        int addLen = maxLength - len;
        StringBuilder prefix = new StringBuilder();
        while (addLen-- > 0) {
            prefix.append("0");
        }

        return prefix.toString() + binary;
    }
    
    private String toBinary(long number) {
        StringBuilder binary = new StringBuilder();
        
        while (number > 0) {
            binary.append(number % 2);
            number /= 2;
        }
        
        binary.reverse();
        
        return binary.toString();
    }
    
    private int getMaxLength(int len) {
        // 7 -> 7, 8 -> 15
        int maxLength = 1;
        while (len > 0) {
            len /= 2;
            maxLength *= 2;
        }
        
        return (maxLength - 1);
    }
}