#include <iostream>
#include <cstring>

using namespace std;

int main()
{
    char a[100] = { 0 };
    string b;
    int count=-1;

    cin >> b;
    b.copy(a, b.length(),0);

    for (int i = 97; i < 123; i++)
    {
        for (int loc = 0; loc < strlen(a); loc++)
        {
            if ((int)a[loc] == i)
            {
                count = loc;
                break;
            }
        }
        cout << count << " ";
        count = -1;
    }

    return 0;
}