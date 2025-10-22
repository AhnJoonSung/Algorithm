import java.util.*;

/*
양의 정수 n(1,2,3)
n -> k(2)진수 로 변환
-> 변환된 수 안에 다음 조건의 소수가 몇개?

[조건]
1. 소수 양쪽에 0 존재 -> 
2. 왼쪽은 시작, 오른쪽에 0 -> 처음부터 0 나올때까지
3. 왼쪽에 0, 오른쪽은 끝 -> 뒤에서부터 0 나올때까지
4. 소수만으로 이루어짐 -> 전체
<단, P는 0을 포함하지 않음>

=> 주어진 문자열에서 0으로 분리해서 소수인지 
*/
class Solution {
    static final int MAX = 1_000_000;
        
    public int solution(int n, int k) {        
        String kNum = convert(n, k);
        
        System.out.println(kNum);
        
        String[] parts = kNum.split("0");
        
        List<String> partList = new ArrayList<>();
        
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty())
                continue;
            partList.add(part);
        }
        
        int answer = 0;
        for (String part: partList) {
            long num = Long.parseLong(part);
            
            if (isPrime(num))
                answer++;
        }
        
        return answer;
    }
    
    // 211 2 1 1 11
    private boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        
        long end = (long)Math.sqrt(n);
        for (long l = 2; l <= end; l++) {
            if (n % l == 0)
                return false;
        }
        
        return true;
    }
    
    private String convert(int n, int k) {
        String result = "";
        
        while (n > 0) {
            int remain = n % k;
            n /= k;
            
            result = (char)(remain + '0') + result;
        }
        
        return result;
    }
}