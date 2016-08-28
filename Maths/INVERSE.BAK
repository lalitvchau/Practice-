#include<conio.h>
#include<iostream.h>
class matrix
 {
   float **p;
   int m,n;
   public:
   matrix(){}
    matrix(int x,int y)
    {
      m=x;n=y;
      cout<<endl<<"enter Your Matrix"<<m<<"*"<<n<<endl;
      *p=new float[m];
      for(int i=0;i<m;i++)
	{
	  p[i]=new float[n];
	  for(int j=0;j<n;j++)
	   {
	     cout<<"\nMat["<<i<<"]["<<j<<"]= ";
	     cin>>p[i][j];
	    }
	  }
       }
  void matDisplay()
    {
      cout<<"\n Your Matrix["<<m<<"]["<<n<<"]=\n";
      for(int i=0;i<m;i++)
       {
	 for(int j=0;j<n;j++)
	    cout<<"\t"<<p[i][j];
	 cout<<endl;
       }
    }
    operator-()
    {
     if(m*n==9)
     {
      float A,adj[3][3];
      A=(p[0][0]*((p[1][1]*p[2][2])-(p[1][2]*p[2][1])))-(p[0][1]*((p[1][0]*p[2][2])-(p[1][2]*p[2][0])))+(p[0][2]*((p[1][0]*p[2][1])-(p[1][1]*p[2][0])));
       {
	 adj[0][0]=(p[1][1]*p[2][2])-(p[1][2]*p[2][1]);
	 adj[1][0]=-1*((p[1][0]*p[2][2])-(p[1][2]*p[2][0]));
	 adj[2][0]=(p[1][0]*p[2][1])-(p[1][1]*p[2][0]);
	 adj[0][1]=-1*((p[0][1]*p[2][2])-(p[0][2]*p[2][1]));
	 adj[1][1]=(p[0][0]*p[2][2])-(p[0][2]*p[2][0]);
	 adj[2][1]=-1*((p[0][0]*p[2][1])-(p[0][1]*p[2][0]));
	 adj[0][2]=(p[0][1]*p[1][2])-(p[0][2]*p[1][1]);
	 adj[1][2]=-1*((p[0][0]*p[1][2])-(p[0][2]*p[1][0]));
	 adj[2][2]=(p[0][0]*p[1][1])-(p[0][1]*p[1][0]);
	}
	for(int i=0;i<m;i++)
       {
	 for(int j=0;j<n;j++)
	   p[i][j]=(adj[i][j]/A);
	}

      }
    else if(m*n==4)
     {
      float A,adj[2][2];
      A=(p[0][0]*p[1][1])-(p[0][1]*p[1][0]);
      adj[0][0]=p[1][1];
      adj[0][1]=(-1*(p[0][1]));
      adj[1][0]=(-1*(p[1][0]));
      adj[1][1]=p[0][0];
      for(int i=0;i<m;i++)
       {
	 for(int j=0;j<n;j++)
	   p[i][j]=(adj[i][j]/A);
	}
      }
    else
      {
	cout<<"OverLoaed Matrix Worng matrix choice!";
       }

  }
 };
 void main()
  {
   int m,n;
   clrscr();
   cout<<"Enter order=";
   cin>>m;
   n=m;
   matrix t1(m,n);
   clrscr();
   cout<<"Your Matrix=\n";
   t1.matDisplay();
   -t1;
   t1.matDisplay();
   getch();
 }