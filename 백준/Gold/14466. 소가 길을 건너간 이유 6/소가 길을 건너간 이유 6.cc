#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;

int N, K, R;
bool visited[101][101];
bool roads[101][101][4] = { false, };
int dy[] = {-1,0,1,0};
int dx[] = {0,1,0,-1};

vector<pii> cows;
int result = 0;

void bfs(int y, int x) {
	queue<pii> q;
	q.push({ y,x });
	visited[y][x] = true;

	while (!q.empty()) {
		int cy = q.front().first;
		int cx = q.front().second;
		q.pop();


		for (int i = 0; i < 4; i++) {
			if (roads[cy][cx][i])
				continue;
			int ny = cy + dy[i];
			int nx = cx + dx[i];

			if (ny < 1 || ny > N || nx < 1 || nx > N || visited[ny][nx])
				continue;
			q.push({ ny,nx });
			visited[ny][nx] = true;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> K >> R;
	int r, c, r2, c2;
	for (int i = 0; i < R; i++) {
		cin >> r >> c >> r2 >> c2;
		for (int j = 0; j < 4; j++) {
			int nr = r + dy[j];
			int nc = c + dx[j];
			if (nr == r2 && nc == c2) {
				roads[r][c][j] = true;
				roads[r2][c2][(j + 2) % 4] = true;
			}
		}
	}

	for (int i = 0; i < K; i++) {
		cin >> r >> c;
		cows.push_back({ r,c });
	}

	for (int i = 0; i < K; i++) {
		memset(visited, 0, sizeof visited);

		bfs(cows[i].first, cows[i].second);

		for (int j = i + 1; j < K; j++) {
			if (visited[cows[j].first][cows[j].second] == 0) {
				result++;
			}
		}
	}
	cout << result << "\n";
}