#include <bits/stdc++.h>

using namespace std;

int R,C;

vector<vector<char>> items;
vector<vector<int>> dp;
int maxl;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void DFS(int y, int x,int count,int visited) {
	visited |= (1 << items[y][x]);
	if (dp[y][x] == visited)
		return;
	maxl = max(maxl, count);

	dp[y][x] = visited;

	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (nx < 0 || nx >= C || ny < 0 || ny >= R)
			continue;
		if (!(visited & (1 << items[ny][nx]))) {
			DFS(ny, nx, count + 1,visited);
		}
	}

}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> R >> C;
	items = vector<vector<char>>(R, vector<char>(C));
	dp = vector<vector<int>>(R, vector<int>(C));
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> items[i][j];
		}
	}
	maxl = 1;
	DFS(0, 0,1, 0);

	cout << maxl;
	return 0;
}
