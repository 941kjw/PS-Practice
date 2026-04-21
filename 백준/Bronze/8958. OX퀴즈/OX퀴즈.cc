#include <stdio.h>

#include <string.h>
int main() 
{
	int i, cnt = 0, sum = 0, n, length = 0, j;
	char OX[80] = " ";//총 80길이
	scanf("%d", &n);//몇회 반복할까?
	for (i = 0; i < n; i++) //조건문
	{
		scanf("%s", OX);//정답 입력
		length = strlen(OX);//길이는?
		sum = 0; cnt = 0;//초기화
		for (j = 0; j < length; j++) //길이만큼 비교
		{
			if (OX[j] == 'X')
			{
				cnt = 0;//연속처치 초기화
			}
			else 
			{
				++cnt;
				sum += cnt;//연속처치 스택 1 추가
			}
		}
		printf("%d\n", sum);//결과점수
	}
}
