#include <bits/stdc++.h>

using namespace std;

int n, m;
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
pair<int, int> target;
vector<vector<int>> mapInfo;
vector<vector<int>> distInfo;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	mapInfo = vector<vector<int>>(n, vector<int>(m));
	distInfo = vector<vector<int>>(n, vector<int>(m,-1));
	int x;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> x;
			if (x == 2)
				target = { i,j };
			if (!x)
				distInfo[i][j] = 0;
			mapInfo[i][j] = x;
		}
	}

	queue<pair<int, int>> q;
	q.push(target);
	distInfo[target.first][target.second] = 0;

	while (!q.empty()) {
		pair<int, int> cur = q.front();
		q.pop();
		int curdist = distInfo[cur.first][cur.second];

		for (int i = 0; i < 4; i++) {
			int ny = cur.first + dy[i];
			int nx = cur.second + dx[i];

			if (nx < 0 || ny < 0 || nx >= m || ny >= n)
				continue;
			if (mapInfo[ny][nx] != 0 && distInfo[ny][nx] == -1) {
				distInfo[ny][nx] = distInfo[cur.first][cur.second] + 1;
				q.push({ ny,nx });
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << distInfo[i][j] << ' ';
		}
		cout << '\n';
	}

	return 0;
}
