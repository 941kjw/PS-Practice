#include <iostream>
#include <queue>

using namespace std;

int tomatoes[101][101][101];

int N;
int M;
int H;

int dx[6] = { 1,-1,0,0,0,0 };
int dy[6] = { 0,0,1,-1,0,0 };
int dz[6] = { 0,0,0,0,1,-1 };

struct point3D{
	int x;
	int y;
	int z;
};

point3D make_point(int x, int y, int z)
{
	point3D returnPoint;
	returnPoint.x = x;
	returnPoint.y = y;
	returnPoint.z = z;
	return returnPoint;
}

queue<point3D> tomatoQueue;

void BFS()
{
	int count = 0;
	while (!tomatoQueue.empty())
	{
		int size = tomatoQueue.size();
		count++;

		for (int i = 0; i < size; i++)
		{
			int x = tomatoQueue.front().x;
			int y = tomatoQueue.front().y;
			int z = tomatoQueue.front().z;

			tomatoQueue.pop();

			for (int i = 0; i < 6; i++)
			{
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];

				if (nx >= 0 && nx < H && ny >= 0 && ny < M && nz >= 0 && nz < N && tomatoes[nx][ny][nz] == 0) {
					tomatoes[nx][ny][nz] = 1;
					tomatoQueue.push(make_point(nx, ny, nz));
				}
			}
		}
	}

	for (int i = 0; i < H; i++)
	{
		for (int j = 0; j < M; j++)
		{
			for (int k = 0; k < N; k++)
			{
				if (tomatoes[i][j][k] == 0)
				{
					cout << -1;
					return;
				}
			}

		}
	}
	cout << count - 1;
	return;
}


int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	cin >> M;
	cin >> H;
	
	for (int i = 0; i < H; i++)
	{
		for (int j = 0; j < M; j++)
		{
			for (int k = 0; k < N; k++)
			{
				cin >> tomatoes[i][j][k];
				if (tomatoes[i][j][k] == 1)
				{
					tomatoQueue.push(make_point(i, j, k));
				}
			}
			
		}
	}

	BFS();

	return 0;
}