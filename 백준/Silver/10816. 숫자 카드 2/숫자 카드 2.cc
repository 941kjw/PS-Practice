#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, num;
int numCount = 0;
vector<int> numCards;

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;
	for (int i = 0; i < N; i++)
	{
		cin >> num;
		numCards.push_back(num);
	}
	sort(numCards.begin(), numCards.end());

	cin >> M;
	for (int i = 0; i < M; i++)
	{
		numCount = 0;
		cin >> num;
		
		cout << upper_bound(numCards.begin(),numCards.end(),num) - lower_bound(numCards.begin(), numCards.end(), num) << ' ';
	}
	return 0;
}