#include <iostream>

using namespace std;

int M, N;
char board[50][50];

int Min(int a, int b) {
	if (a < b)return a; return b;
}
void GetBoard()
{
	cin >> N >> M;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M ; j++)
		{
			cin >> board[i][j];
		}
	}
}
int makewhiteboard(int x, int y)
{
	int count = 0;
	for (int i = x; i < x + 8; i++)
	{
		for (int j = y; j < y + 8; j++)
		{
			if (i == x || i == x + 2 || i == x + 4 || i == x + 6)
			{
				if (j == y || j == y + 2 || j == y + 4 || j == y + 6)
				{
					if (board[i][j] != 'W') count++;
				}
				else
				{
					if (board[i][j] != 'B') count++;
				}
			}
			else
			{
				if (j == y || j == y + 2 || j == y + 4 || j == y + 6)
				{
					if (board[i][j] != 'B') count++;
				}
				else
				{
					if (board[i][j] != 'W') count++;
				}
			}

		}
	}
	return count;
}
int makeblackboard(int x, int y)
{
	int count = 0;
	for (int i = x; i < x + 8; i++)
	{
		for (int j = y; j < y + 8; j++)
		{
			if (i == x || i == x + 2 || i == x + 4 || i == x + 6)
			{
				if (j == y || j == y + 2 || j == y + 4 || j == y + 6)
				{
					if (board[i][j] != 'B') count++;
				}
				else
				{
					if (board[i][j] != 'W') count++;
				}
			}
			else
			{
				if (j == y || j == y + 2 || j == y + 4 || j == y + 6)
				{
					if (board[i][j] != 'W') count++;
				}
				else
				{
					if (board[i][j] != 'B') count++;
				}
			}

		}
	}
	return count;
}

void MakeBoard()
{
	int Whiteleftmin;
	int blackleftmin;
	int total = 9999;
	for (int i = 0; i < N; i++)
	{
		if (i + 8 > N) break;
		for (int j = 0; j < M; j++)
		{
			if (j + 8 > M) break;
			Whiteleftmin = makewhiteboard(i, j);
			blackleftmin = makeblackboard(i, j);
			total = Min(total, Min(Whiteleftmin, blackleftmin));
		}
	
	}
	cout << total << '\n';
}


void Work()
{
	GetBoard();
	MakeBoard();
}


int main()
{
	Work();
	return 0;
}