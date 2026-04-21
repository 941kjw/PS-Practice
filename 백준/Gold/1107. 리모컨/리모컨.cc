#include <iostream>
#include <vector>
using namespace std;
vector<bool> numpad;

int check(int n)
{
	if (n == 0) {
		if (numpad[n])
			return 0;
		else
			return 1;
	}
	int len = 0;

	while (n>0)
	{
		if (numpad[n % 10])
			return 0;
		n /= 10;
		len++;
	}

	return len;
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n, m;
	numpad = vector<bool>(10, false);
	cin >> n >> m;

	while (m--)
	{
		int a;
		cin >> a;
		numpad[a] = true;
	}
	int result = abs(n - 100);

	for (int i = 0; i <= 1000000; i++)
	{
		int len = check(i);
		if (len > 0) {
			int press = abs(i - n);
			if (result > press + len)
				result = press + len;
		}
	}

	cout << result;

	return 0;
}