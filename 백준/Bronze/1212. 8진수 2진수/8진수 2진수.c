#include <stdio.h>

#include <string.h>

int main()
{
	char a[333335];
	int length;
	scanf("%s", a);
	length = strlen(a);
	if (a[0] == '1')
		printf("1");
	else if (a[0] == '2')
		printf("10");
	else if (a[0] == '3')
		printf("11");
	else if (a[0] == '4')
		printf("100");
	else if (a[0] == '5')
		printf("101");
	else if (a[0] == '6')
		printf("110");
	else if (a[0] == '7')
		printf("111");
    else if(a[0]=='0')
        printf("0");
	for (int i = 1; i < length; i++)
	{
		if (a[i] == '1')
			printf("001");
		else if (a[i] == '2')
			printf("010");
		else if (a[i] == '3')
			printf("011");
		else if (a[i] == '4')
			printf("100");
		else if (a[i] == '5')
			printf("101");
		else if (a[i] == '6')
			printf("110");
		else if (a[i] == '7')
			printf("111");
		else if (a[i] == '0')
			printf("000");
	}
	return 0;
}