#include <iostream>
#include <vector>
#include<algorithm>

using namespace std;

int linesize;
int arr[25][25];
bool visited[25][25];
int house_cnt;
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

vector<int> house;

void DFS(int x, int y)
{
	house_cnt++;
	visited[x][y] = true;

	for (int i = 0; i < 4; i++)
	{
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || ny < 0 || nx >= linesize || ny >= linesize)
			continue;
		if (arr[nx][ny] == 1 && visited[nx][ny] == false)
			DFS(nx, ny);
	}
}

int main()
{
	cin >> linesize;
	for (int i = 0; i < linesize; i++)
	{
		string temp;
		cin >> temp;
		for (int j = 0; j < linesize; j++)
		{
			arr[i][j] = temp[j] - '0';
		}
	}

	for (int i = 0; i < linesize; i++)
	{
		for (int j = 0; j < linesize; j++)
		{
			if (arr[i][j] == 1 && visited[i][j] == false)
			{
				house_cnt = 0;
				DFS(i, j);
				house.push_back(house_cnt);
			}
		}
	}

	sort(house.begin(), house.end());
	cout << house.size() << '\n';
	for (int i : house)
		cout << i << '\n';
}
