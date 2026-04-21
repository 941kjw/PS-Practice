#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
 
struct CUSTOMER
{
    int Customer_Number;
    int Time;
    int Counter_Number;
};
 
int N, K;
vector<pair<int, int>> Customer;
 
struct PQ_Cmp
{
    bool operator()(CUSTOMER A, CUSTOMER B)
    {
        if (A.Time == B.Time)
        {
            if (A.Counter_Number > B.Counter_Number)
            {
                return true;
            }
            return false;
        }
        else
        {
            if (A.Time > B.Time)
            {
                return true;
            }
            return false;
        }
    }
};
 
bool Cmp(CUSTOMER A, CUSTOMER B)
{
    if (A.Time == B.Time)
    {
        if (A.Counter_Number > B.Counter_Number)
        {
            return true;
        }
        return false;
    }
    else
    {
        if (A.Time < B.Time)
        {
            return true;
        }
        return false;
    }
}
 
void Input()
{
    cin >> N >> K;
    for (int i = 0; i < N; i++)
    {
        int a, b; cin >> a >> b;
        Customer.push_back(make_pair(a, b));
    }
}
 
void Solution()
{
    priority_queue<CUSTOMER, vector<CUSTOMER>, PQ_Cmp> PQ;
    vector<CUSTOMER> Exit_Order;
    bool Flag = false;
    for (int i = 0; i < K; i++)
    {
        if (i == N)
        {
            Flag = true;
            break;
        }
        int Custom_Num = Customer[i].first;
        int Custom_Time = Customer[i].second;
        PQ.push({ Custom_Num, Custom_Time, i + 1 });
    }
 
    if (Flag == true)
    {
        while (PQ.empty() == 0)
        {
            Exit_Order.push_back(PQ.top());
            PQ.pop();
        }
    }
    else
    {
        for (int i = K; i < N; i++)
        {
            int Custom_Num = Customer[i].first;
            int Custom_Time = Customer[i].second;
            PQ.push({ Custom_Num, Custom_Time + PQ.top().Time, PQ.top().Counter_Number });
            Exit_Order.push_back(PQ.top());
            PQ.pop();
        }
 
        while (PQ.empty() == 0)
        {
            Exit_Order.push_back(PQ.top());
            PQ.pop();
        }
    }
 
    sort(Exit_Order.begin(), Exit_Order.end(), Cmp);
    long long Answer = 0;
    for (int i = 0; i < Exit_Order.size(); i++)
    {
        int Number = Exit_Order[i].Customer_Number;
        long long Result = (long long)((i + 1)) * (long long)Number;
        Answer += Result;
    }
    cout << Answer << endl;
}
 
void Solve()
{
    Input();
    Solution();
}
 
int main(void)
{
    //freopen("Input.txt", "r", stdin);
    Solve();
    return 0;
}
