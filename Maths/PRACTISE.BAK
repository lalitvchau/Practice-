#include<conio.h>
#include<iostream.h>
class matrix
 {
  int **p,m,n;
  public:
  matrix(){}
  matrix(int x,int y);
  void display(void);
  operator int();
  ~matrix(){}
   };
matrix::matrix(int x,int y)
   {  m=x;n=y;
     *p=new int[m];
     for(int i=0;i<m;i++)
       {
	 p[i]=new int[n];
	 for(int j=0;j<n;j++)
	  {
	   cout<<"mat["<<i<<"]["<<j<<"]= ";
	   cin>>p[i][j];
	   }
	 }
     }
void matrix::display()
   {
    for(int i=0;i<m;i++)
     {
       cout<<endl;
       for(int j=0;j<n;j++)
	 cout<<"\t"<<p[i][j];
     }
    }
matrix::operator int()
    {
      int val=0;
      for(int i=0;i<m;i++)
       {
	 for(int j=0;j<n;j++)
	 {
	  if(i==j)
	   {
	     if(p[i][j]!=0)
	       {
	       val=0;
	       break;
		}
	     val=2;
	   }
	  else if((p[i][j]+p[j][i])==0){val=2;}
	  else{val=0; break;}
	 }
       }
      if(val==0)
       {
       for(i=0;i<m;i++)
       {
	 for(int j=0;j<n;j++)
	 {
	  if(p[i][j]==p[j][i]){val=1;}
	  else{val=0; break;}
	 }
       }
       }
      return val;
     }
void main()
  {
   int m,n,fleg=0;
   clrscr();
   cout<<"Enter Matrix for cheak symmetric or skew symmetric =\n";
   cout<<"enter order m*n=";
   cin>>m;cin>>n;
   matrix t(m,n);
   clrscr();
   cout<<"You Have Enter Matrix=\n";
   t.display();
   fleg=t;
   if(fleg==1)
       cout<<"\nYour matrix is SYMMETRIC !";
   else if(fleg==2)
       cout<<"\nYour matrix is SKEW SYMMETRIC !";
   else
       cout<<"\nYour matrix is neighter SYMMETRIC nor SKEW SYMMETRIC !";
   getch();
  }