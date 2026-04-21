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
	int num;
	string order;
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		cin >> order;
		if (!order.compare("push"))
		{
			cin >> num;
			numstack.push(num);
		}
		else if (!order.compare("top"))
		{
			if (numstack.empty())
				cout << -1 << '\n';
			else
				cout << numstack.top() << '\n';
		}
		else if (!order.compare("pop"))
		{
			if (numstack.empty())
				cout << -1 << '\n';
			else
			{
				cout << numstack.top() << '\n';
				numstack.pop();
			}
		}
		else if (!order.compare("size"))
			cout << numstack.size() << '\n';
		else if (!order.compare("empty"))
		{
			if (numstack.empty())
				cout << 1 << '\n';
			else
				cout << 0 << '\n';
		}
	}

	return 0;

}