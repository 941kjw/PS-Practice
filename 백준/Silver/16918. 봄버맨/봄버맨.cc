#include <iostream>

using namespace std;

char map[201][201];
int timer[201][201];

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

void solve(int x,int y,int time)
{
	int time_counter = 2;
	while (true)
	{
		if (time_counter == time + 1)
			break;
		if (time_counter % 2 == 0)
		{
			for(int i=0;i<x;i++)
				for (int j = 0; j < y; j++)
				{
					if (map[i][j] == 'O')
						continue;
					map[i][j] = 'O';
					timer[i][j] = time_counter + 3;
				}

		}
		else
		{
			for (int i = 0; i < x; i++)
			{
				for (int j = 0; j < y; j++)
				{
					if (timer[i][j] == time_counter)
					{
						for (int k = 0; k < 4; k++)
						{
							int nx = i + dx[k];
							int ny = j + dy[k];

							if (nx < 0 || nx >= x || ny < 0 || ny >= y)
								continue;
							if (map[nx][ny] == '.')
								continue;
							map[nx][ny] = '.';
						}
						map[i][j] = '.';
						timer[i][j] = 0;
					}
				}
			}
		}
		time_counter++;
	}
	for (int i = 0; i < x; i++)
	{
		for (int j = 0; j < y; j++)
		{
			cout << map[i][j];
		}
		cout << '\n';
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int x, y, time;
	cin >> x >> y >> time;

	for(int i=0;i<x;i++)
		for (int j = 0; j < y; j++)
		{
			cin >> map[i][j];
			if (map[i][j] == 'O')
				timer[i][j] = 3;
		}

	solve(x,y,time);
}