#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int T, l, sx, sy, tox, toy;
vector<vector<int>> chess;

int dx[8] = { -1,-2,-2,-1,1,2,2,1 };
int dy[8] = { -2,-1,1,2,2,1,-1,-2 };

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> T;
	while (T--)
	{
		cin >> l;
		chess = vector<vector<int>>(l, vector<int>(l));
		cin >> sx >> sy;

		cin >> tox >> toy;
		queue<pair<int, int>> q;
		vector<vector<int>> visited(l, vector<int>(l));

		q.push({ sx,sy });
		visited[sx][sy] = 1;

		while (!q.empty())
		{
			int curx = q.front().first;
			int cury = q.front().second;
			q.pop();

			for (int i = 0; i < 8; i++)
			{
				int nx = curx + dx[i];
				int ny = cury + dy[i];
				if (nx < 0 || nx >= l || ny < 0 || ny >= l)
					continue;
				
				if (visited[nx][ny] == 0)
				{
					q.push({ nx,ny });
					visited[nx][ny] = visited[curx][cury] + 1;
				}


			}
		}

		cout << visited[tox][toy] -1 << '\n';
	}
}