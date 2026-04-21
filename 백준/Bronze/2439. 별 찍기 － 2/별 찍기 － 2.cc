#include <iostream>
using namespace std;
int main()
{
	int a, n,k,s;
	a = 0;
	n = 0;
	k = 0;
	s = 0;
	cin >> n;
	k = n;
	while (s<n)
	{	
		while (k>s+1)
		{
			cout << ' ';
			k=k-1;
		}
		while (a<=s)
		{
			cout<< '*';
			a++;
		}	
		s++;
		
		k = n;
		a = 0;
		cout << endl;
	}
	return 0;
}