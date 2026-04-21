#include <iostream>

using namespace std;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	cout.tie(NULL);
	int strength;
	int retreat;
	int total;
	int result;
	cin >> strength >> retreat >> total;
	if ((total - strength) % (strength - retreat) == 0)
		result = (total - strength) / (strength - retreat);
	else
		result = (total - strength) / (strength - retreat) + 1;
	cout << result + 1;
}