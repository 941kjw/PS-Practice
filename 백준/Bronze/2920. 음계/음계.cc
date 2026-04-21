#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	int mode = -1;
	int former;

	for (int i = 0; i < 8; i++)
	{
		cin >> n;
		if (i == 0)
		{
			former = n;
			continue;
		}
		if (former < n && mode <0)
			mode = 0;
		else if (former > n && mode < 0)
			mode = 1;
		if ((former < n && mode == 1) || (former > n && mode == 0))
			mode = 2;
		former = n;
	}

	switch (mode)
	{
	case 0:
		cout << "ascending" << '\n';
		break;


	case 1:
		cout << "descending" << '\n';
		break;


	case 2:
		cout << "mixed" << '\n';
		break;
	}


	return 0;
}