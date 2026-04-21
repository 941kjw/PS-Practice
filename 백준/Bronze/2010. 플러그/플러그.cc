#include <iostream>

using namespace std;

int main()
{
	int a=0, b = 0, c=0, d=0, final = 0;
	c = 0;
	cin >> a;
	while (b < a)
	{
		cin >> d;
		final = final + d;
		b++;
	}
	final = final - a + 1;
	cout << final<<endl;
	return 0;
}