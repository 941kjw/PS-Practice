#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N;
vector<int> answer(1000001);
vector<int> seq(1000001);
stack<int> s;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		int c;
		cin >> c;
		seq[i] = c;
	}
	for (int i = N - 1; i >= 0; i--)
	{
		while (!s.empty() && s.top() <= seq[i])
			s.pop();
		if (s.empty())
			answer[i] = -1;
		else
			answer[i] = s.top();
		s.push(seq[i]);
	}
	for (int i = 0; i < N; i++)
		cout << answer[i] << ' ';
	return 0;

}