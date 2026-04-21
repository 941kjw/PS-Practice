#include <iostream>
#include <stack>

using namespace std;

int N;


int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	string order;
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		stack<char> charstack;
		cin >> order;
		for (int i = 0; i < order.length(); i++)
		{
			if (order.at(i) == '(')
				charstack.push('(');
			else
			{
				if (charstack.empty())
				{
					charstack.push('(');
					break;
				}
				else
					charstack.pop();
			}
				
		}

		if (charstack.empty())
			cout << "YES" << '\n';
		else
			cout << "NO" << '\n';
	}

	return 0;

}