#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

int toSec(string date) {
    stringstream ss(date);
    
    int h, m;
    char colon;
    
    ss >> h >> colon >> m;
    
    return h * 60 + m;
}

int getTerm(string in, string out) {
    int inSec = toSec(in);
    int outSec = toSec(out);
    
    return abs(outSec - inSec);
}

int calcFee(int total, vector<int>& fees) {
    int base = fees[0];
    int baseFee = fees[1];
    int unitTime = fees[2];
    int unitFee = fees[3];
    
    if (total <= base)
        return baseFee;
    
    int addTime = (total - base);
    
    int cnt = addTime / unitTime;
    if (addTime % unitTime != 0)
        cnt++;
    
    return baseFee + (unitFee * cnt);
}

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    
    map<string, string> inAt;
    map<string, int> sum;
    
    for (string record: records) {
        stringstream ss(record);
        string word;
        
        vector<string> datas;
        while (ss >> word) {
            datas.push_back(word);
        }
        
        string date = datas[0];
        string car = datas[1];
        string behaviour = datas[2];
        
        if ("IN" == behaviour) {
            inAt[car] = date;
        } else {
            string inDate = inAt[car];
            string outDate = date;
            
            int spendTime = getTerm(inDate, outDate);
            sum[car] = sum[car] + spendTime;
            inAt.erase(car);
        }
    }
    
    for (auto& [car, inDate]: inAt) {
        sum[car] += getTerm(inAt[car], "23:59");
    }
    
    for (auto& [car, time]: sum) {
        int fee = calcFee(time, fees);
        answer.push_back(fee);
    }
    
    return answer;
}

