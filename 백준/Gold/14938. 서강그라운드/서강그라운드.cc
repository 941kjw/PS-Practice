#include <iostream>

using namespace std;


int n, m, r;
int dist[101][101];
int items[101];
int results[101];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m >> r;

	fill(&dist[1][1], &dist[n][n], 99999);

	for (int i = 1; i <= n; i++) {
		cin >> items[i];
		dist[i][i] = 0;
	}

	for (int i = 0; i < r; i++) {
		int a, b, l;
		cin >> a >> b >> l;
		dist[a][b] = l;
		dist[b][a] = l;
	}

	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}

	int imax = -1;
	
	for (int i = 1; i <= n; i++) {
		int sum = 0;
		for (int j = 1; j <= n; j++) {
			if (dist[i][j] <= m)
				sum += items[j];
		}
		imax = max(imax, sum);
		results[i] = sum;
	}

	cout << imax;

	return 0;
}