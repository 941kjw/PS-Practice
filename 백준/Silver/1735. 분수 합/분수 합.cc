#include <iostream>
using namespace std;
int num1, num2, r;
int gcd(int num1, int num2)
{
	if (num2 == 0)
		return num1;
	else
		return gcd(num2, num1%num2);
}
int main(void)
{
	int t1, t2, b1, b2,tf,bf,s;
	cin >> t1 >> b1;
	cin >> t2 >> b2;
	tf = t1*b2 + b1*t2;
	bf = b1*b2;
	s = gcd(tf, bf);
	tf /= s;
	bf /= s;
	cout << tf << ' ' << bf << endl;
	return 0;
}