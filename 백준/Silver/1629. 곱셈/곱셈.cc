#include <iostream>

using namespace std;

long long A, B, C;

long long recursive(long long num, long long times)
{
	if (times == 1)
		return num;
	else
	{
		long long temp = recursive(num, times / 2);
		if (times % 2)
			return ((temp * temp) % C * num) % C;
		else
			return (temp * temp) % C;
	}
}

int main()
{
	cin >> A >> B >> C;
	
	cout << recursive(A % C, B) << '\n';
}