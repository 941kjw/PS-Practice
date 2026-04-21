#include <iostream>
#include <vector>
using namespace std;

int N, M, K;
int parents[51];
vector<int> know;
vector<vector<int>> v(50);

int Find(int x)
{
	if (parents[x] == x)
		return x;
	return x = Find(parents[x]);
}

void Union(int x, int y)
{
	x = Find(x);
	y = Find(y);
	parents[x] = y;
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M >> K;

	while (K--)
	{
		int t;
		cin >> t;
		know.push_back(t);
	}
	
	for (int i = 1; i <= N; i++)
		parents[i] = i;

	for (int i = 0; i < M; i++)
	{
		int p;
		cin >> p;
		int num, prev;

		for (int j = 0; j < p; j++)
		{
			if (j)
			{
				prev = num;
				cin >> num;
				Union(prev, num);
			}
			else
				cin >> num;
			v[i].push_back(num);
		}
	}

	for (auto& list : v)
	{
		bool flag = false;

		for (auto& person : list)
		{
			if (flag)
				break;
			for (auto& t : know) {
				if (Find(person) == Find(t)) {
					flag = true;
					break;
				}
			}
		}
		if (flag)
			M--;
	}

		
	
	cout << M;
	return 0;
}