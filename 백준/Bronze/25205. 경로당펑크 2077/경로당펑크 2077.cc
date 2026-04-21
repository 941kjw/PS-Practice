#include <iostream>

using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	string s;
	string filtered = "yuiophjklbnm";
	cin >> n;
	cin >> s;

	for (int i = 0; i < filtered.length(); i++)
	{
		if (s.at(n-1) == filtered.at(i))
		{
			cout << 0 << '\n';
			return 0;
		}
	}
	cout << 1 << '\n';
	return 0;
}