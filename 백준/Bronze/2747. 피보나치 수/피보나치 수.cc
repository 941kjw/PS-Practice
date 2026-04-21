#include <iostream>
using namespace std;
int main()
{
	int a, b, n,sum;
	a = 0, b = 1,sum=0;
	cin >> n;
	if (n == 1) {
		cout << 1 << endl;
	}
	else {
		while (n-->=2)
		{
			sum = a + b;
			a = b;
			b = sum;
		}
		cout << sum << endl;
	}
	return 0;
}