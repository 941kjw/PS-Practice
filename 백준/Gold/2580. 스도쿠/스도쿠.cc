#include <iostream>
#include <vector>

using namespace std;

int board[9][9];
vector<pair<int, int>> empty_Points;
int cnt = 0;
bool completed = false;

bool check(pair<int, int> point)
{
	int square_X = point.first / 3;
	int square_Y = point.second / 3;
	for (int i = 0; i < 9; i++)
	{
		if (board[point.first][i] == board[point.first][point.second] && i != point.second)// 가로줄에 같은 녀석이 있다!!
			return false;
		if (board[i][point.second] == board[point.first][point.second] && i != point.first)// 세로줄에 같은 녀석이 있다!!
			return false;
	}

	for (int i = 3 * square_X; i < 3 * square_X + 3; i++) // 이 3*3 방안에 같은 녀석이 있다!!
	{
		for (int j = 3 * square_Y; j < 3 * square_Y + 3; j++)
		{
			if (board[i][j] == board[point.first][point.second])
				if (i != point.first && j != point.second)
					return false;
		}
	}
	return true;
}

void sudoku(int num)
{
	if (num == cnt)// 모든 빈칸을 다 채웠다!!
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				cout << board[i][j] << ' ';
			}
			cout << '\n';
		}
		completed = true; // 뒷 내용 모두 스킵
		return;// 즉시 탈출
	}
	for (int j = 1; j <= 9; j++)
	{
		board[empty_Points[num].first][empty_Points[num].second] = j; // 1~9까지 일단 넣어보고..
		if (check(empty_Points[num])) // 체크해서 괜찮으면 다음 위치로.
			sudoku(num + 1);
		if (completed) // 스킵 지점.
			return;
	}
	board[empty_Points[num].first][empty_Points[num].second] = 0; //만약 모두 시도했는데 안된다면.. 전 위치로 돌아가서 다시..(이전 호출의 for문으로)
	return;
}

int main()
{
	pair<int, int> point;
	for (int i = 0; i < 9; i++)
	{
		for (int j = 0; j < 9; j++)
		{
			cin >> board[i][j];
			if (board[i][j] == 0)
			{
				cnt++;
				point.first = i;
				point.second = j;
				empty_Points.push_back(point);
			}
		}
	}
	sudoku(0);
}