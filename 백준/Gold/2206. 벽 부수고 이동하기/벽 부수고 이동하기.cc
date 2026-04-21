#include <iostream>
#include <queue>

using namespace std;

int N;
int M;
int map[1002][1002] = { 0, };
int visited[1002][1002][2] = { 0, };

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

int BFS()
{
	queue<pair<pair<int,int>, int>> probe;
	probe.push(make_pair(make_pair(1,1),1));
	visited[1][1][1] = 1;

	while (!probe.empty())
	{
		int x = probe.front().first.first;
		int y = probe.front().first.second;
		int chance = probe.front().second;

		probe.pop();

		if (x == N && y == M)
			return visited[x][y][chance];

		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx <= 0 || nx > N || ny <= 0 || ny > M)
				continue;
			if (map[nx][ny] == 1 && chance)
			{
				visited[nx][ny][chance - 1] = visited[x][y][chance] + 1;
				probe.push(make_pair(make_pair(nx, ny), chance - 1));
			}
			if (map[nx][ny] == 0 && visited[nx][ny][chance]==0)
			{
				visited[nx][ny][chance] = visited[x][y][chance] + 1;
				probe.push(make_pair(make_pair(nx, ny),chance));
			}
		}
	}
	return -1;
}


int main()
{
	cin >> N;
	cin >> M;

	for (int i = 1; i <= N; i++)
	{
		string temp;
		cin >> temp;
		for (int j = 0; j < M; j++)
		{
			map[i][j + 1] = temp[j] - '0';
		}
	}
	
	cout << BFS();

}