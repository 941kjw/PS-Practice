#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> board;
int n, m;
int answer = 0;
int dx[] = { 1,-1,0,0 };
int dy[] = { 0,0,-1,1 };

int standard(int x, int y, int prev_x, int prev_y, int depth) {
	int rval = 0;
	if (depth == 3) {
		return board[x][y];
	}
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
			continue;
		}
		if (prev_x == nx && prev_y == ny) {
			continue;
		}

		rval = max(rval, board[x][y] + standard(nx, ny, x, y, depth + 1));
	}
	return rval;
}

int ahhShape(int x, int y) {
	int answer = 0;
	if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < m) {
		int temp = board[x - 1][y] + board[x][y - 1] + board[x][y + 1] + board[x][y];
		answer = max(answer, temp);
	}
	if (x - 1 >= 0 && y - 1 >= 0 && x + 1 < n) {
		int temp = board[x - 1][y] + board[x][y - 1] + board[x + 1][y] + board[x][y];
		answer = max(answer, temp);
	}
	if (x + 1 < n && y - 1 >= 0 && y + 1 < m) {
		int temp = board[x + 1][y] + board[x][y - 1] + board[x][y + 1] + board[x][y];
		answer = max(answer, temp);
	}
	if (x - 1 >= 0 && y + 1 < m && x + 1 < n) {
		int temp = board[x - 1][y] + board[x][y + 1] + board[x+1][y] + board[x][y];
		answer = max(answer, temp);
	}
	return answer;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	

	
	cin >> n >> m;
	board = vector<vector<int>>(n, vector<int>(m));

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			answer = max(answer, standard(i, j, -1, -1, 0));
			answer = max(answer, ahhShape(i, j));
		}
	}

	cout << answer << '\n';
}