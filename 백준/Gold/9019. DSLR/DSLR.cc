#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int t, a, b;



int D(int num)
{
	return (num * 2) % 10000;
}

int S(int num)
{
	return num - 1 < 0 ? 9999 : num - 1;
}

int L(int num)
{
	return (num % 1000) * 10 + (num / 1000);
}

int R(int num)
{
	return (num / 10) + (num % 10) * 1000;
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> t;

	for (int i = 0; i < t; i++)
	{
		cin >> a >> b;
		queue<pair<int,string>> q;
		bool visited[10000] = {false,};
		q.push({a,""});
		visited[a] = true;
		int cur;
		string s;
		while (!q.empty())
		{
			cur = q.front().first;
			s = q.front().second;
			q.pop();
			
			if (cur == b)
			{
				cout << s << '\n';
				break;
			}

			int dcur = D(cur);
			if (!visited[dcur])
			{
				visited[dcur] = true;
				q.push({ dcur ,s + "D"});
			}

			int scur = S(cur);
			if (!visited[scur])
			{
				visited[scur] = true;
				q.push({ scur ,s + "S" });
			}

			int lcur = L(cur);
			if (!visited[lcur])
			{
				visited[lcur] = true;
				q.push({ lcur ,s + "L" });
			}
			
			int rcur = R(cur);
			if (!visited[rcur])
			{
				visited[rcur] = true;
				q.push({ rcur ,s + "R" });
			}
		}
	}

	return 0;
}