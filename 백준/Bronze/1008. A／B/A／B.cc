#include<iostream>
#include<iomanip>

using namespace std;

int main()
{
	long double a;
	long double b;

	cin >> a;
	cin >> b;

	cout << setprecision(20);
	cout << a / b;

	return 0;
}