import java.util.*;

class Solution {
    static List<User> userList;
    static List<Result> resultList;
    static List<Amount> amounts;
    /*
    1순위 가입자 수
    2순위 판매액
    
    사용자: 자기 기준에 따라 모두 이모티콘 구매
    사용자: 구매비용이 일정가격을 넘으면 취소하고 이모티콘 플러스 가입
    */
    public int[] solution(int[][] users, int[] emoticons) {        
        userList = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            User user = new User(users[i][0], users[i][1]);
            userList.add(user);
        }
        
        amounts = new ArrayList<>();
        for (int value = 10; value <= Amount.FORTY.value(); value += 10) {
            Amount amount = Amount.valueOf(value);
            amounts.add(amount);
        }
        
        resultList = new ArrayList<>();
        backtrack(0, emoticons, new ArrayList<>());
        
        resultList.sort(
            Comparator.comparing(Result::getPeople, Comparator.reverseOrder())
            .thenComparing(Result::getTotalMoney, Comparator.reverseOrder())
        );
        Result result = resultList.get(0);
        
        int[] answer = new int[]{result.people, result.totalMoney};
        return answer;
    }
    
    private void backtrack(int idx, int[] emoticons, List<Amount> selected) {
        if (idx == emoticons.length) {
            Result result = getResult(selected, emoticons);
            resultList.add(result);
            return;
        }
        
        
        for (int i = 0; i < amounts.size(); i++) {
            Amount amount = amounts.get(i);
            selected.add(amount);
            backtrack(idx + 1, emoticons, selected);
            selected.remove(selected.size() - 1);
        }
    }
    
    private Result getResult(List<Amount> selected, int[] emoticons) {
        Result result = new Result();
        
        for (User user: userList) {
            int totalPrice = 0;
            
            for (int i = 0; i < emoticons.length; i++) {
                int price = emoticons[i];
                int amountValue = selected.get(i).value();

                if (amountValue >= user.discount) {
                    totalPrice += (price * (100 - amountValue) / 100);
                }
            }

            if (totalPrice >= user.price) {
                result.people++;
            } else {
                result.totalMoney += totalPrice;
            }
        }
        
        return result;
    }
}

class Result {
    int people, totalMoney;
    
    public Result() {
        people = 0;
        totalMoney = 0;
    }
    
    public Result(int p, int t) {
        people = p;
        totalMoney = t;
    }
    
    public int getPeople() {return people;}
    public int getTotalMoney() {return totalMoney;}
}

class User {
    int discount;
    int price;
    
    public User(int d, int p) {
        discount = d;
        price = p;
    }
}

enum Amount {
    TEN(10),
    TWENTY(20),
    THIRTY(30),
    FORTY(40);
    
    private final int value;
    
    Amount(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }
    
    public static Amount valueOf(int value) {
        for (Amount amount: values()) {
            if (amount.value() == value)
                return amount;
        }
        
        throw new IllegalArgumentException();
    }
}