#include <iostream>
#include <vector>
using namespace std;


int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int creamPasta[200001];
	int dish[200001];
	int fork;
	int yummy;

	cin >> fork;

	for (int i = 0; i < fork; i++)
	{
		cin >> creamPasta[i];
	}

	dish[0] = 0;
	yummy = creamPasta[0];

	for (int i = 1; i < fork; i++)
	{
		dish[i] = dish[i - 1];
		yummy = min(yummy, creamPasta[i]);
		dish[i] = max(dish[i], creamPasta[i] - yummy);
	}
	

	for (int i = 0; i < fork; i++)
		cout << dish[i] << ' ';

	return 0;
}