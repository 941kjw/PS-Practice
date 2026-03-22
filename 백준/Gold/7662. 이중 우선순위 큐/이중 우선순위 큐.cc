#include <iostream>
#include <queue>
#include <set>
#include <map>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int T,Q;
	char c;
	int num;
	cin >> T;
	for (int i = 0; i < T; i++)
	{
		priority_queue<int> maxs;
		priority_queue<int,vector<int>, greater<int>> mins;
		map<int, int> deleted;
		cin >> Q;
		for (int i = 0; i < Q; i++)
		{
			cin >> c;
			if (c == 'I'){
				cin >> num;
				maxs.push(num);
				mins.push(num);
				if (deleted.count(num) == 0)
					deleted[num] = 1;
				else
					deleted[num]++;
			}
			else {
				cin >> num;
				if (num > 0 && !maxs.empty())
				{
					while (!maxs.empty() && deleted[maxs.top()] == 0)
					{
						maxs.pop();
					}
					if (!maxs.empty())
					{
						deleted[maxs.top()]--;
						maxs.pop();
					}
				}
				else if (num < 0 && !mins.empty())
				{
					while (!mins.empty() && deleted[mins.top()] == 0)
					{
						mins.pop();
					}
					if (!mins.empty())
					{
						deleted[mins.top()]--;
						mins.pop();
					}
				}
			}
		}
		while (!maxs.empty() && deleted[maxs.top()] == 0)
		{
			maxs.pop();
		}

		while (!mins.empty() && deleted[mins.top()] == 0)
		{
			mins.pop();
		}

		if (maxs.empty() || mins.empty())
			cout << "EMPTY\n";
		else
			cout << maxs.top() << ' ' << mins.top() << '\n';
	}


	return 0;
}