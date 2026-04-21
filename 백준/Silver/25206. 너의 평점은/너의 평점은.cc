#include <iostream>

using namespace std;

long double calcscore(string s)
{
	double result;
	if (s.at(0) == 'F')
		return 0;
	result = 'E' - s.at(0);
	if (s.at(1) == '+')
		result += 0.5;
	return result;
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	long double sum = 0;
	long double score;
	long double basescore = 0;
	string grade;
	long double multsum;
	string name;

	for (int i = 0; i < 20; i++)
	{
		cin >> name >> score >> grade;
		if (grade != "P")
		{
			sum += score * calcscore(grade);
			basescore += score;
		}
	}

	multsum = sum / basescore;
	cout << multsum << '\n';

	return 0;
}