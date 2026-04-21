#include <iostream>

using namespace std;

int main()
{
    int a;
    char b;
    int total=0;
    cin >> a;
    for (int i = 0; i < a; i++)
    {
        cin >> b;
        total = total + (int)b -48;
    }
    cout << total << endl;
    return 0;
}