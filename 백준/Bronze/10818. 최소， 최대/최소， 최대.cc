#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int c;
	int n;
	int nmin = 99999999;
	int nmax = -99999999;

	cin >> c;
	for (int i = 0; i < c; i++)
	{
		cin >> n;
		nmin = min(n, nmin);
		nmax = max(n, nmax);
	}

	cout << nmin << ' ' << nmax;
	return 0;
}