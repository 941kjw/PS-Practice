#include <iostream>
#include <stack>

using namespace std;

int N;
stack<int> numstack;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	int last = 0;
	string answer;
	while (N--)
	{
		int c;
		cin >> c;
		if (c > last)
		{
			while (c > last)
			{
				numstack.push(++last);
				answer += '+';
			}
			numstack.pop();
			answer += '-';
		}
		else
		{
			bool success = false;
			if (!numstack.empty())
			{
				if (c == numstack.top())
				{
					success = true;
				}
				numstack.pop();
				answer += '-';
			}
			if (!success)
			{
				cout << "NO" << '\n';
				return 0;
			}
		}
	}
	for (int i = 0; i < answer.length(); i++)
	{
		cout << answer.at(i) << '\n';
	}

	return 0;

}