#include <iostream>
#include <vector>

using namespace std;

int num;
int answer;
int dx[] = { 1,-1,0,0 };
int dy[] = { 0,0,1,-1 };
vector<pair<int, int>> emp_pt, teach_pt;
char cellar[6][6];

int check()
{
	for (pair<int, int> t : teach_pt)
	{
		for (int i = 0; i < 4; i++)
		{
			int nx = t.first;
			int ny = t.second;
			while (true)
			{
				nx += dx[i];
				ny += dy[i];
				if (nx<0 || nx>num - 1 || ny<0 || ny>num - 1 || cellar[nx][ny] == 'O')
					break;
				if (cellar[nx][ny] == 'S')
					return 0;
			}
		}
	}
	return 1;
}

void dfs(int idx, int cnt)
{
	if (cnt == 3)
	{
		answer += check();
		return;
	}
	for (int i = idx + 1; i < emp_pt.size(); i++)
	{
		int x = emp_pt[i].first;
		int y = emp_pt[i].second;
		cellar[x][y] = 'O';
		dfs(i, cnt + 1);
		cellar[x][y] = 'X';
	}
}

int main()
{
	cin >> num;
	for (int i = 0; i < num; i++)
	{
		for (int j = 0; j < num; j++)
		{
			cin >> cellar[i][j];
			if (cellar[i][j] == 'X')
				emp_pt.push_back(make_pair(i, j));
			else if(cellar[i][j] == 'T')
				teach_pt.push_back(make_pair(i, j));
		}
	}
	dfs(-1, 0);
	if (answer == 0)cout << "NO";
	else cout << "YES";
}