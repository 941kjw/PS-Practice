#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, sum = 0;
vector<int> queue;

int main()
{

	cin >> N;
	for (int i = 0; i < N; i++)
	{
		int temp;
		cin >> temp;
		queue.push_back(temp);
	}

	sort(queue.begin(), queue.end());

	for (int i = N-1; i >0; i--)
	{
		for (int j = 0; j < i; j++)
		{
			queue[i] += queue[j];
		}
	}

	for (int i = 0; i < N; i++)
	{
		sum += queue[i];
	}

	cout << sum;
}