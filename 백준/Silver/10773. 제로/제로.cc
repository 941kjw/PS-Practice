#include <iostream>
#include <stack>

using namespace std;

int k;
stack<int> zerostack;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int num;
	cin >> k;
	for (int i = 0; i < k; i++)
	{
		cin >> num;
		if (num)
		{
			zerostack.push(num);
		}
		else
		{
			if (!zerostack.empty())
				zerostack.pop();
		}
	}
	num = 0;
	while (!zerostack.empty())
	{
		num += zerostack.top();
		zerostack.pop();
	}
	cout << num << '\n';

	return 0;

}