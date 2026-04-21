#include <iostream>

using namespace std;

int main()
{
    int a;
    int b;
    int c;
    cin>>c;
    for(int i=1;i<=c;i++)
    {
        cin>>a;
        cin>>b;
        cout<<"Case #"<<i<<": "<<a+b<<'\n';
    }
}