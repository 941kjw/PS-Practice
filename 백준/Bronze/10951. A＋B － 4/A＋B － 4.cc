#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int a, b;

	while (!(cin >> a >> b).eof())
	{
		cout << a + b << '\n';
	}
	return 0;
}