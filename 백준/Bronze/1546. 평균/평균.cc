#include <iostream>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	float c;
	float average = 0;
	float maxs = -1;
	cin >> c;
	vector<float> scores = vector<float>(c, 0);

	for (int i = 0; i < c; i++)
	{
		cin >> scores[i];
		maxs = max(scores[i], maxs);
		average += scores[i];
	}

	average /= c;
	average /= maxs;
	average *= 100;

	cout << average << '\n';

	return 0;
}