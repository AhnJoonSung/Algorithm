#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false); // 빠른 입출력
    cin.tie(0);

    int n;
    cin >> n;
    vector<int> A(n), B(n), C(n), D(n);
    for (int i = 0; i < n; ++i) {
        cin >> A[i] >> B[i] >> C[i] >> D[i];
    }

    vector<int> AB, CD;
    AB.reserve(n * n);
    CD.reserve(n * n);

    // A + B 조합
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            AB.push_back(A[i] + B[j]);

    // C + D 조합
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            CD.push_back(C[i] + D[j]);

    sort(AB.begin(), AB.end());

    long long count = 0;

    // 각 CD 값에 대해 -(C+D)가 AB에 몇 개 있는지 찾기
    for (int sum : CD) {
        int target = -sum;
        auto lower = lower_bound(AB.begin(), AB.end(), target);
        auto upper = upper_bound(AB.begin(), AB.end(), target);
        count += (upper - lower);
    }

    cout << count << '\n';
    return 0;
}
