#include <iostream>
#include <vector>
#include <queue>
using namespace std;


int N, K;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<int> visited(100000);
	cin >> N >> K;

	queue<int> q;

	q.push(N);
	visited[N] = 1;
	while (!q.empty())
	{
		int cur = q.front();
		q.pop();

		if (cur == K)
		{
			break;
		}

		if (cur - 1 >= 0 && visited[cur - 1] == 0)
		{
			q.push(cur - 1);
			visited[cur - 1] = visited[cur] + 1;
		}
		if (cur + 1 <= 100000 && visited[cur + 1] == 0)
		{
			q.push(cur + 1);
			visited[cur + 1] = visited[cur] + 1;
		}
		if (2 * cur <= 100000 && visited[2 * cur] == 0)
		{
			q.push(2 * cur);
			visited[2 * cur] = visited[cur] + 1;
		}
	}
	cout << visited[K] -1 << '\n';
}