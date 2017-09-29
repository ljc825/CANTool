#include <cstdio>
//定义CAN信息的结构体
struct message
{
    int id,len,cnt;
    char message_name[32],node_name[32];
}m[1010];
//定义CAN信号的结构体
struct sign
{
    int start,len,dir;
    double a,b,c,d;
    char signal_name[32],unit[32],node_name[32];
}s[1010];
int cnt=0,total=0,temp;
char flag[32];
//对CAN描述数据库进行调整，使数据格式能够更加便于iava语言的读入
int main()
{
    freopen("data.in","r",stdin);
    freopen("data.txt","w",stdout);
    while(scanf("%s",flag)!=EOF)
    {
        if(flag[0]=='B')
        {
            cnt++;
            temp=cnt-1;
            scanf("%d %s  %d %s",&m[temp].id,m[temp].message_name,
                  &m[temp].len,m[temp].node_name);
            m[temp].cnt=0;
        }
        else
        {
            scanf("%s : %d|%d@%d%*c (%lf,%lf) [%lf|%lf] %s %s",
                  s[total].signal_name,&s[total].start,
                  &s[total].len, &s[total].dir, &s[total].a,
                   &s[total].b, &s[total].c, &s[total].d,
                    s[total].unit,s[total].node_name);
            total++;
            m[temp].cnt++;
        }
    }
    total=0;
    printf("%d\n\n",cnt);
    for(int i=0;i<cnt;i++)
    {
        printf("%s %d %d\n",m[i].message_name,m[i].id,m[i].len);
        printf("%d\n\n",m[i].cnt);
        for(int j=0;j<m[i].cnt;j++)
        {
            printf("%s %d %d %d %.1f %.1f %.1f %.1f %s\n",
                   s[total].signal_name,s[total].start,
                   s[total].len,s[total].dir,s[total].a,
                   s[total].b,s[total].c,s[total].d,s[total].unit);
            total++;
        }
        puts("");
    }

}
