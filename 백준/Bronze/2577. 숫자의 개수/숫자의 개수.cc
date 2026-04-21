#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int a,b,c;
	int abc;
	vector<int> numbers = vector<int>(10, 0);
	cin >> a >> b >> c;

	abc = a * b * c;

	while (abc > 0)
	{
		numbers[abc % 10]++;
		abc /= 10;
	}

	for (int i = 0; i < 10; i++)
	{
		cout << numbers[i] << '\n';
	}
	
	return 0;
}