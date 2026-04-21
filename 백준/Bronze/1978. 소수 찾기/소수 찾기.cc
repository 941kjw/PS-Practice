#include <iostream>

using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int n, num, count = 0, i;
	cin >> n;
	while (n--)
	{
		cin >> num;
		for (i = 2; i < num; i++)
		{
			if (num % i == 0)
				break;	
		}
		if (i == num)
			count++;
	}
	cout << count;

	return 0;

}