#include<iostream>


using namespace std;

int main()
{
	int cardcount;
	int objectnum;

	int* card;
	int result=0;

	cin >> cardcount;
	cin >> objectnum;
	card = new int[cardcount];
	for (int i = 0; i < cardcount; i++)
	{
		cin >> card[i];
	}
	for (int j = 0; j < cardcount; j++)
	{
		int total = 0;
		for (int k = 0; k < cardcount; k++)
		{
			total = card[j];
			if (j == k) continue;
			if (total + card[k] > objectnum) continue;
			
			for (int l = 0; l < cardcount; l++)
			{
				total = card[j] + card[k];
				if (j == l || k == l) continue;
				if (total + card[l] > objectnum) continue;
				total += card[l];
				if (result < total) result = total;
			}
		}
	}

	cout << result << '\n';
	return 0;
}