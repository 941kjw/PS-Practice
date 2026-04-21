#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	int c = 0;
	vector<bool> isPresent = vector<bool>(42, false);

	for (int i = 0; i < 10; i++)
	{
		cin >> n;
		if (!isPresent[n % 42])
		{
			c++;
			isPresent[n % 42] = true;
		}
	}

	cout << c;
	return 0;
}