#include<iostream>

using namespace std;

int main()
{
	int naturalnum;
	bool success = false;
	int trynum = 0;
	int total=0;
	cin >> naturalnum;

	for (int i = 0; i < naturalnum; i++)
	{
		int j = trynum;
		while (j != 0)
		{
			total += j % 10;
			j /= 10;
		}
		if (total+trynum == naturalnum)
		{
			success = true;
			cout << trynum << '\n';
			break;
		}
		trynum++;
		total = 0;
	}
	if (!success) cout << '0' << '\n';


	return 0;
}