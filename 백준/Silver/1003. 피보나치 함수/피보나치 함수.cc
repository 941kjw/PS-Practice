#include <iostream>

using namespace std;

int fiboarr[50] = { 0,1, };

int fibo(int num)
{
	if (num == 0 || num == 1) 
	{
		return fiboarr[num];
	}
	else if (fiboarr[num] == 0) //아직 계산한적이 없다면..?
	{
		fiboarr[num] = fibo(num - 1) + fibo(num - 2); // 재귀호출로 저장해두기
	}
	return fiboarr[num]; // 이전예 계산한적이 있다면 그걸 반환.
}

int main()
{
	int num;
	cin >> num;
	int temp;
	for (int i = 0; i < num; i++) {
		cin >> temp;
		if (temp == 0)
		{
			cout << "1 0" << '\n';
		}
		else
			cout << fibo(temp - 1) << ' ' << fibo(temp) << '\n';
	}
}