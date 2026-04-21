#include <iostream>

using namespace std;

int main()
{
    int A;
    int B;
    int totalnum;
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin>>totalnum;
    for(int i=0;i<totalnum;i++)
    {
        cin>>A;
        cin>>B;
        cout<<A+B<<'\n';
    }
}