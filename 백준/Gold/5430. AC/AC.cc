#include <iostream>
#include <queue>
#include <string>
using namespace std;

int main()
{
	int T;
	string p;
	int n;
	string x;

	cin >> T;
	while (T--)
	{
		deque<int> dq;
		cin >> p;
		cin >> n;
		cin >> x;
		int i = 1;
		while (x[i] != '\0')
		{
			int k = 0;
			while (x[i] >= '0' && x[i] <= '9')
			{
				k *= 10;
				k += int(x[i] - '0');
				i++;
			}
			if (k != 0)
				dq.push_back(k);
			i++;
		}
		bool errflag = false;
		bool reverseflag = true;
		i = 0;
		while (p[i] != '\0')
		{
			if (p[i] == 'R')
				reverseflag = !reverseflag;
			else
			{
				if (dq.empty())
				{
					cout << "error\n";
					errflag = true;
					break;
				}
				if (reverseflag)
					dq.pop_front();
				else
					dq.pop_back();
			}
			i++;
		}
		if (!errflag)
			cout << '[';
		while(!dq.empty())
		{
			if (reverseflag)
			{
				int c = dq.front();
				dq.pop_front();
				cout << c;
			}
			else
			{
				int c = dq.back();
				dq.pop_back();
				cout << c;
			}
			if (!dq.empty())
				cout << ',';
		}
		if (!errflag)
			cout << "]\n";
	}
}