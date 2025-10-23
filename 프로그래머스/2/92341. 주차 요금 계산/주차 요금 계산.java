import java.util.*;

/*
요금표: 
입차기록
출차기록

[목표]
차량별 주차 요금

*/

class Solution {
    static final String MAX_TIME = "23:59";
    
    static int baseTime, baseFee, unit, unitFee;
    
    public int[] solution(int[] fees, String[] records) {
        baseTime = fees[0];
        baseFee = fees[1];
        unit = fees[2];
        unitFee = fees[3];
        
        Map<String, String> inAt = new HashMap<>();
        Map<String, Integer> feeMap = new HashMap<>();
        Map<String, Integer> accTime = new HashMap<>();
        
        for (int i = 0; i < records.length; i++) {
            String record = records[i];
            
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNum = parts[1];
            String moving = parts[2];
            
            if ("IN".equals(moving)) {
                inAt.put(carNum, time);
            } else {
                String in = inAt.get(carNum);
                String out = time;
                
                int newAccTime = accTime.getOrDefault(carNum, 0) + getDist(in, out);
                accTime.put(carNum, newAccTime);
                
                inAt.remove(carNum);
            }
        }
        
        for (String carNum: inAt.keySet()) {
            String in = inAt.get(carNum);
            
            int spendTime = getDist(in, MAX_TIME);
            int newAccTime = accTime.getOrDefault(carNum, 0) + spendTime;
            
            accTime.put(carNum, newAccTime);
        }
        
        List<String> carNumList = new ArrayList<>(accTime.keySet());
        Collections.sort(carNumList);
        
        int[] answer = new int[carNumList.size()];
        for (int i = 0; i < answer.length; i++) {
            int curAccTime = accTime.get(carNumList.get(i));
            answer[i] = getFee(curAccTime);
        }
        
        return answer;
    }
    
    private int getDist(String in, String out) {
        return Math.abs(normalize(in) - normalize(out));
    }
    
    private int getFee(int spendTime) {
        if (spendTime <= baseTime)
            return baseFee;
        
        int remainTime = spendTime - baseTime;
        int count = remainTime / unit;
        if (remainTime % unit != 0)  
            count++;
        return baseFee + (count) * unitFee;
    }
    
    private int normalize(String time) {
        String[] parts = time.split(":");
        
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        
        return 60 * hour + minute;
    }
}