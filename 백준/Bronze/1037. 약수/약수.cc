#include <iostream>
using namespace std;
int main()
{
	int a, b, result,s,l,temp,c;
	cin >> a;
	cin >> c;
	s = c;
	l = c;
	for (b = 1; b < a; b++)
	{
		cin >> c;
		if (c <= s)
		{
			s = c;
		}
		if (c >= l)
		{
			l = c;
		}

	}
	result = l*s;
	cout << result<<endl;
	return 0;
}