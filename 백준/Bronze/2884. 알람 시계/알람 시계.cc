#include <iostream>

using namespace std;

int main()
{
    int hour;
    int min;
    cin>>hour;
    cin>>min;
    min -= 45;
    if(min<0)
    {
        hour--;
        min+=60;
        if(hour<0)
            hour+=24;
    }
    cout<<hour<<' '<<min;
}