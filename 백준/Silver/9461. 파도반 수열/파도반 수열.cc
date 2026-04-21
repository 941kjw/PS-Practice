#include <iostream>

using namespace std;

long long padovan_save[101] = { 0, };

long long padovan(int n)
{
	long long & result = padovan_save[n];
	if (result != 0) return result;
	if (n == 1 || n == 2 || n == 3)
		result = 1;
	else
		result = padovan(n - 2) + padovan(n - 3);
	return result;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int testcase;
	int num;
	cin >> testcase;
	for (int i = 0; i < testcase; i++)
	{
		cin >> num;
		cout << padovan(num) << '\n';
	}
}