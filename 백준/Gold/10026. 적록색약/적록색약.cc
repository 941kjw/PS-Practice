#include <iostream>
#include <vector>

using namespace std;

int N;

vector<vector<char>> board;
vector<vector<bool>> visited;

int dx[] = { 1,-1,0,0 };
int dy[] = { 0,0,1,-1 };

void dfs(int x, int y) {
	visited[x][y] = true;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			continue;
		if (!visited[nx][ny] && board[x][y] == board[nx][ny]) {
			dfs(nx, ny);
		}
	}
}


int main() {

	int countN = 0;
	int countI = 0;
	cin >> N;

	board = vector<vector<char>>(N, vector<char>(N));
	visited = vector<vector<bool>>(N, vector<bool>(N));

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visited[i][j])
			{
				dfs(i, j);
				countN++;
			}
		}
	}
	visited = vector<vector<bool>>(N, vector<bool>(N));

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (board[i][j] == 'G')
				board[i][j] = 'R';
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visited[i][j])
			{
				dfs(i, j);
				countI++;
			}
		}
	}

	cout << countN << ' ' << countI << '\n';
}