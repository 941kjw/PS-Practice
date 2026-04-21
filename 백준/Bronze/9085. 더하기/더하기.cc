#include <iostream>
using namespace std;
int main()
{
	int q=0, w=0, e=0, r=0, t=0, y=0, sum=0;
	cin >> q;
	while (w < q)
	{
		cin >> e;
		while (r <e)
		{
			cin >> t;
			sum = sum + t;
			r++;
		}
		cout << sum << endl;
		sum = 0;
		w++;
		r = 0;
	}
	return 0;
}