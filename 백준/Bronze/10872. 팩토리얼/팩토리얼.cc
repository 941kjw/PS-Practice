#include <iostream>

using namespace std;

int factorial(int num)
{
    if (num == 1)
        return 1;
    if (num == 0)
        return 1;
    return num * factorial(num-1);
}

int main()
{
    int a;
    cin >> a;
    cout << factorial(a);
}