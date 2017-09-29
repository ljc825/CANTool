#include <cstdio>
#include<iostream>
using namespace std;
//定义CAN信号的结构体
struct sign
{
    int start,len,dir;
    double a,b,c,d;
    char signal_name[32],unit[32],node_name[32];
};
//定义CAN信息的结构体
struct message
{
    int id,len,cnt;
    char message_name[32],node_name[32];
    sign s[30];
}m[1010];


int cnt=0,cnt1=0,temp;
char flag[32];

int main()
{
    freopen("data.in","r",stdin);
    freopen("data2.txt","w",stdout);
    while(scanf("%s",flag)!=EOF)
    {
        if(flag[0]=='B')
        {
            cnt++;
            temp=cnt-1;
            scanf("%d %s %d %s",&m[temp].id,m[temp].message_name,
                  &m[temp].len,m[temp].node_name);
            cnt1=m[temp].cnt=0;
        }
        else
        {
            scanf("%s : %d|%d@%d%*c (%lf,%lf) [%lf|%lf] %s %s",
                  m[temp].s[cnt1].signal_name,&m[temp].s[cnt1].start,
                  &m[temp].s[cnt1].len, &m[temp].s[cnt1].dir, &m[temp].s[cnt1].a,
                   &m[temp].s[cnt1].b, &m[temp].s[cnt1].c, &m[temp].s[cnt1].d,
                    m[temp].s[cnt1].unit,m[temp].s[cnt1].node_name);
            cnt1++;
            m[temp].cnt++;
        }
    }
    for(int i=0;i<cnt;i++)
    {
        long long check=-1;
        int pos1,pos2;
        for(int j=0;j<m[i].cnt;j++)
        {
            pos1=m[i].s[j].start/8;
            pos2=m[i].s[j].start%8;
            if(m[i].s[j].dir==0)
            {
                for(int k=0;k<m[i].s[j].len;k++)
                {
                    //cout<<pos1*8+pos2<<endl;
                    check^=((long long)1<<(pos1*8+pos2));
                    pos2--;
                    if(pos2==-1)
                    {
                        pos2=7;
                        pos1++;
                    }
                }
            }
            else
            {
                for(int k=0;k<m[i].s[j].len;k++)
                {
                    check^=((long long)1<<(pos1*8+pos2));
                    pos2++;
                    if(pos2==8)
                    {
                        pos2=0;
                        pos1++;
                    }
                }

            }

        }
        cout<<m[i].id<<" "<<check<<endl;
    }


}
