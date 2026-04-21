#include <iostream>

using namespace std;

int result;
int num;
int cache[1000001] = { 0,1,2, };

int make(int num)
{
	for (int i = 3; i <= num; i++)
	{
		cache[i] = cache[i - 1] + cache[i - 2];
		cache[i] = cache[i] % 15746;
	}
	return cache[num];
}


int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	cin >> num;
	result = make(num);
	cout << result;
}