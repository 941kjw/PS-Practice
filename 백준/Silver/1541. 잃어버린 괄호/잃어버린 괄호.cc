#include <iostream>
#include <string>

using namespace std;





int main()
{
	string fomular;
	cin >> fomular;

	int result = 0;
	bool minus = false;
	string temp = "";

	for (int i = 0; i <= fomular.size(); i++)
	{
		if (fomular[i] == '+' || fomular[i] == '-' || fomular[i] == '\0')
		{
			if (minus)
				result -= stoi(temp);
			else
				result += stoi(temp);
			temp = "";
			if (fomular[i] == '-')
				minus = true;
			continue;
		}
		temp += fomular[i];
	}

	cout << result;
}