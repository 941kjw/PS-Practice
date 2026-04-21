#include <iostream>
#include <queue>

using namespace std;

int tomatoes[1001][1001];
int width;
int height;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

queue<pair<int, int>> tomatoQueue;

void BFS()
{
	while (!tomatoQueue.empty())
	{
		int x = tomatoQueue.front().first;
		int y = tomatoQueue.front().second;

		tomatoQueue.pop();

		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= height || ny < 0 || ny >= width)
				continue;
			if (tomatoes[nx][ny] == 0) {
				tomatoes[nx][ny] = tomatoes[x][y] + 1;
				tomatoQueue.push(make_pair(nx, ny));
			}
				
		}
	}
}


int main()
{
	
	cin >> width;
	cin >> height;

	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			cin >> tomatoes[i][j];
			if (tomatoes[i][j] == 1)
			{
				tomatoQueue.push(make_pair(i, j));
			}
		}
	}
	BFS();
	int max = 0;
	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			if (tomatoes[i][j] == 0)
			{
				cout << "-1" << '\n';
				return 0;
			}
			if (max < tomatoes[i][j])
				max = tomatoes[i][j];
		}
	}
	cout << max - 1 << '\n';

	return 0;
}