#include <iostream>

using namespace std;

int land[51][51] = { 0, };
bool visited[51][51];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };


int linesize_x;
int linesize_y;
int baechoo_count;
int wormcount = 0;

void DFS(int x, int y)
{
	visited[x][y] = true;

	for (int i = 0; i < 4; i++)
	{
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || ny < 0 || nx >= linesize_x || ny >= linesize_y)
			continue;
		if (land[nx][ny] == 1 && !visited[nx][ny])
			DFS(nx, ny);
	}

}

int main()
{
	int testcase;
	cin >> testcase;
	for (int i = 0; i < testcase; i++)
	{
		cin >> linesize_x >> linesize_y >> baechoo_count;
		
		for (int i = 0; i < linesize_x; i++)
		{
			for (int j = 0; j < linesize_y; j++)
			{
				land[i][j] = 0;
				visited[i][j] = false;
			}
		}

		for (int i = 0; i < baechoo_count; i++)
		{
			int x, y;
			cin >> x >> y;
			land[x][y] = 1;
		}	

		for (int i = 0; i < linesize_x; i++)
		{
			for (int j = 0; j < linesize_y; j++)
			{
				if (land[i][j] == 1 && visited[i][j] == false)
				{
					wormcount++;
					DFS(i, j);
				}
			}
		}
		cout << wormcount << '\n';
		wormcount = 0;
	}
}