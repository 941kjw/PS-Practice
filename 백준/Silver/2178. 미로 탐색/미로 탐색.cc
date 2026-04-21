#include <iostream>
#include <queue>

using namespace std;

int maze[101][101] = { 0, };
bool visited[101][101];
int linesize_x;
int linesize_y;
int direction[4][2] = { {0,-1},{1,0},{0,1} ,{-1,0} };



void BFS()
{
	queue<pair<int, int>> way;
	way.push(make_pair(0,0));
	pair<int, int>cur;


	while (!way.empty())
	{
		cur = way.front();
		way.pop();

		for (int i = 0; i < 4; i++)
		{
			int nx = cur.second + direction[i][0];
			int ny = cur.first + direction[i][1];
			if (0 <= nx && linesize_y > nx && ny >= 0 && linesize_x > ny  && maze[ny][nx]==1)
			{
				way.push(make_pair(ny, nx));
				maze[ny][nx] = maze[cur.first][cur.second] + 1;
			}
		}
	}
}

int main()
{
	cin >> linesize_x >> linesize_y;
	for (int i = 0; i < linesize_x; i++)
	{
		string temp;
		cin >> temp;
		for (int j = 0; j < linesize_y; j++)
		{
			maze[i][j] = temp[j] - '0';
		}
	}
	BFS();

	cout << maze[linesize_x -1][linesize_y -1];
}
