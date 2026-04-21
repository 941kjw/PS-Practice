#include <iostream>
#include <queue>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int t,n, m;
	cin >> t;

	while(t--)
	{
		cin >> n >> m;
		int count = 0;
		priority_queue<int> pq;
		queue<pair<int, int>> q;
		for (int i = 0; i < n; i++)
		{
			int priority;
			cin >> priority;
			q.push({ i,priority });
			pq.push(priority);
		}

		while (!q.empty())
		{
			int index = q.front().first;
			int priority = q.front().second;
			q.pop();

			if (pq.top() == priority)
			{
				pq.pop();
				count++;
				if (m == index)
				{
					cout << count << '\n';
					break;
				}
			}
			else
				q.push({ index,priority });
		}
	}
}