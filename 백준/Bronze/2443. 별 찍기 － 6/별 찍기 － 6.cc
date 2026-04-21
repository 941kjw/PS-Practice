#include <iostream>
using namespace std;
int main()
{
	int a, n, k,s,t;
	a = 0;
	n = 0;
	k = 0;
	t = 0;
	cin >> n;
	s = 2 * n -1;
	while (a<n)
	{
		while (k < a)
		{
			cout << ' ';
			k++;
		}
		while (s >= 1)
		{
			cout << '*';
			s = s - 1;
		}
		
		k = 0;
		t++;
		s = 2 * n - 1 - 2*t;
		cout << endl;
		a++;

	}
	return 0;
}