#include <iostream>
#include <vector>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	cin >> N;
	vector<vector<int>>house(N+1, vector<int>(3));
	house[0][0] = 0;
	house[1][0] = 0;
	house[2][0] = 0;
	int r, g, b;
	for (int i = 1; i <= N; i++)
	{
		cin >> r >> g >> b;

		house[i][0] = min(house[i - 1][1], house[i - 1][2]) + r;
		house[i][1] = min(house[i - 1][0], house[i - 1][2]) + g;
		house[i][2] = min(house[i - 1][0], house[i - 1][1]) + b;
	}
	cout << min(house[N][2], min(house[N][0], house[N][1])) << '\n';
	
}