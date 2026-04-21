#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int a = 1, b = 1;

	while (true)
	{
		cin >> a >> b;
		if (a + b == 0)
			break;
		cout << a + b << '\n';
	}
	return 0;
}