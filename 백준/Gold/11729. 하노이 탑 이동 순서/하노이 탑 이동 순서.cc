#include <iostream>
#include <cmath>

using namespace std;

// 1번에서 2번으로 n-1층을 만들고, 3번에 n번째를 옮기고 다시 n-1층을 쌓기

void hanoi(int num, int from, int to, int through)
{
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    if (num == 1)
        cout << from << " " << to << '\n';
    else
    {
        hanoi(num - 1, from, through, to); //1->2로 3을 통해 n-1개 옮기기
        cout << from << " " << to << '\n'; //1->3으로 n번째 옮기기
        hanoi(num - 1, through, to, from); // 2->3으로 1을 통해 n-1개 옮기기
    }
}

int main()
{
    int a;
    cin >> a;
    cout << (int)pow(2, a) - 1 << '\n'; //2^a-1
    hanoi(a, 1, 3, 2);
}