#include <iostream>
#define MAX 15

using namespace std;

int col[MAX];
int N;
int total = 0;

bool check(int row)
{
	for (int i = 0; i < row; i++)
	{
		if (col[i] == col[row] || abs(col[row] - col[i]) == row - i) //같은 가로줄에 있거나 대각선에 있는가? (대각선 -> x좌표 차이 == y좌표 차이)
			return false;
	}
	return true;
}

void queen(int num)// num은 현재 몇번째 호출에 있는가? or 몇번째 열까지 채웠는가?
{
	if (num == N)// 8열까지 무사히 다 채웠다?
		total++; // 총 경우의 수 하나 증가
	else //채우는 중이라면?
	{
		for (int i = 0; i < N; i++) // 여왕을 몇번째 행에?(가로줄)
		{
			col[num] = i; //(i,num)에 퀸 세워보기
			if (check(num))// 이 자리가 괜찮을까?
				queen(num + 1); //괜찮다면 다음 줄로.. ->돌아올 때 퀸을 다른 자리에 계속 세워보기 (for문 마저 실행)
		}
	}
}

int main()
{
	cin >> N;
	queen(0);//0열부터 시작(세로줄)
	cout << total;
}