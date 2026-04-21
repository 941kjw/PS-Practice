#include<iostream>
#include<iomanip>

using namespace std;

int main()
{
	int casecount;
	int* score;

	
	cout << fixed;
	cout.precision(3);
	cin >> casecount;

	for (int i = 0; i < casecount; i++)
	{
		double ratio;
		int stucount;
		int average;
		int total = 0;
		int numoveraverage=0;
		cin >> stucount;
		score = new int[stucount];
		for (int j = 0; j < stucount; j++)
		{
			cin >> score[j];
			total = total + score[j];
		}
		average = total / stucount;
		for (int j = 0; j < stucount; j++)
		{
			if (score[j] > average)
			{
				numoveraverage++;
			}
		}
		ratio = (double(numoveraverage) / double(stucount)) * 100;
		cout << ratio << "%\n";
		delete[] score;
		total = 0;
	}

	return 0;
}