#include<iostream.h>
#include<conio.h>
class matrix
 {
  int m,n;
  int mat[3][3];
  public:
  void matInsert(int i,int j);
  void display()
    {
      cout<<"\nRESULT=";
       for(int i=0;i<m;i++)
       {
	for(int j=0;j<n;j++)
	 {
	   cout<<"\nMAT["<<i<<"]["<<j<<"]="<<mat[i][j];
	   }
	 }
      }
  void matMult(matrix a,matrix b)
    {
      m=a.m;n=a.n;
      for(int k=0;k<a.m;k++)
	 {
	   for(int l=0;l<a.n;l++)
	     {
	       for(int o=0;o<a.m;o++)
		 {
		    mat[k][l]=mat[k][l]+a.mat[k][l]*b.mat[o][l];
		    }
	       }}
      display();
      }
  void matSub(matrix a,matrix b)
       {
	 m=a.m;
	 n=a.n;
	 for(int i=0;i<m;i++)
	   {
	     for(int j=0;j<n;j++)
	       {
		 mat[i][j]=a.mat[i][j]-b.mat[i][j];
		 }
	    }
	display();
       }
 void matSum(matrix a,matrix b)
       {
	 m=a.m;
	 n=a.n;
	 for(int i=0;i<m;i++)
	   {
	     for(int j=0;j<n;j++)
	       {
		 mat[i][j]=a.mat[i][j]+b.mat[i][j];
		 }
	    }
	    display();
       }
  void matTra(matrix a)
    {
      n=a.m;
      m=a.n;
      for(int i=0;i<m;i++)
       {
	for(int j=0;j<n;j++)
	 {
	  mat[j][i]=a.mat[i][j];
	  }
	}
      display();
    }
  matrix mul(matrix a,int b)
      {
	matrix z;
	z.m=a.m;
	z.n=a.n;
	for(int i=0;i<b;i++)
	  {
	   for(int j=0;j<m;j++)
	    {
	      for(int k=0;k<n;k++)
	       {
		 mat[j][k]=a.mat[j][k]*b;
		 }
	    }
	  }
      return z;
    }
  };
void matrix::matInsert(int i,int j)
  {
    m=i;n=j;
    cout<<"\nInsert Element of Matix"<<i<<"*"<<j;
    for(int k=0;k<i;k++)
     {
       for(int l=0;l<j;l++)
	{
	  cout<<"\nmat["<<k<<"]["<<l<<"]=";
	  cin>>mat[i][j];
	 }
     }
  }

void main()
  {
    clrscr();
    cout<<"Operation First insert and display 3*3 matrix=\n";
    int x=3,y=3;
    matrix ob1;
    ob1.matInsert(3,3);
    ob1.display();
    cout<<"\noperation Second 2A+3B\n";
    matrix ob4,a1,a2,ob2,ob3;
    cout<<"Matrix A\n";
    ob2.matInsert(x,y);
    cout<<"Matrix B\n";
    ob3.matInsert(x,y);
    a1=ob2.mul(ob2,2);
    a2=ob3.mul(ob3,3);
    ob4.matSum(a1,a2);
    cout<<"\noperation third A-2B\n";
    matrix ob5,ob6,ob7;
    cout<<"Matrix A\n";
    ob5.matInsert(x,y);
    cout<<"Matrix B\n";
    ob6.matInsert(x,y);
    a1=ob6.mul(ob6,2);
    ob7.matSub(ob5,a1);
    cout<<"\nOperation fourth matrix multiplication 2*2 matrix\n";
    matrix ob8,ob9;
    ob8.matInsert(2,2);
    ob9.matInsert(2,2);
    matrix ob10;
    ob10.matMult(ob8,ob9);
    cout<<"\nOperation five matrix transpose=\n";
    matrix ob11,ob12;
    ob11.matInsert(2,3);
    ob12.matTra(ob11);
    getch();
}
