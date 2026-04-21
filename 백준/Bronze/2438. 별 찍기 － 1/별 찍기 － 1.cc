#include <iostream>
using namespace std;
int main()
{
	int a, n,k;
	a = 0;
	n = 0;
	k = 0;
	cin >> n;
	while (a<n)
	{
		while (k<=a)
		{
			cout << '*';
			k++;
		}
		k = 0;
		cout << endl;
		a++;

	}
	return 0;
}