#include <stdio.h>

#include <string.h>

int main() {
	char dish[51];
	int i, length, height = 10;
	gets(dish);
	length = strlen(dish);
	for (i = 1; i < length; i++)
	{
		if (dish[i - 1] == dish[i])
		{
			height += 5;
		}
		else if (dish[i - 1] != dish[i])
		{
			height += 10;
		}
	}
	printf("%d\n", height);
}
