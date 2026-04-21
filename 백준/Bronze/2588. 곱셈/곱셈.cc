#include<iostream>

using namespace std;

int main()
{
	int a;
	char b;
	char c;
	char d;

	cin >> a;
	cin.clear();
	cin.ignore();
	cin.get(b);
	cin.get(c);
	cin.get(d);

	cout << a * ((int)d - 48) << '\n';
	cout << a * ((int)c - 48) << '\n';
	cout << a * ((int)b - 48) << '\n';
	cout << (a * (((int)b - 48) * 100)) + (a * ((int)c - 48) * 10) + (a*((int)d - 48)) << '\n';

	return 0;
}