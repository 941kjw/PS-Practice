#include <bits/stdc++.h>

using namespace std;

int R,C;

vector<vector<char>> items;
vector<bool> visited = vector<bool>(26);
int maxl;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void DFS(int y, int x,int count) {
	maxl = max(maxl, count);
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (nx < 0 || nx >= C || ny < 0 || ny >= R)
			continue;
		int charIndex = items[ny][nx] - 'A';
		if (!visited[charIndex]) {
			visited[charIndex] = true;
			DFS(ny, nx, count + 1);
			visited[charIndex] = false;
		}
	}

}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> R >> C;
	items = vector<vector<char>>(R, vector<char>(C));
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> items[i][j];
		}
	}
	maxl = 1;
	visited[items[0][0] - 'A'] = true;
	DFS(0, 0,1);

	cout << maxl;
	return 0;
}
