#include<conio.h>
#include<iostream.h>
class valDet
  {
    int m;
    int **mat;
    public:
    valDet(){}
    valDet(int x)
    {
      m=x;
	cout<<endl<<"enter Your Matrix"<<m<<"*"<<m<<endl;
      *mat=new int[m];
      for(int i=0;i<m;i++)
	{
	  mat[i]=new int[m];
	  for(int j=0;j<m;j++)
	   {
	     cout<<"\nMat["<<i<<"]["<<j<<"]= ";
	     cin>>mat[i][j];
	    }
	  }

       }
    operator int()
     {
      int v;
      if(m*m==4)
       {
	v=(mat[0][0]*mat[1][1])-(mat[0][1]*mat[1][0]);
	 }
      else if(m*m==9)
       {
	v=(mat[0][0]*((mat[1][1]*mat[2][2])-(mat[1][2]*mat[2][1])))-(mat[0][1]*((mat[1][0]*mat[2][2])-(mat[1][2]*mat[2][0])))+(mat[0][2]*((mat[1][0]*mat[2][1])-(mat[1][1]*mat[2][0])));
	 }
      else {cout<<"\n Out of Memory!";return 0;}
	return v;
     }
    void display()
     {
       cout<<"\nMatrix=\n";
       for(int i=0;i<m;i++)
	{
	  cout<<endl;
	  for(int j=0;j<m;j++)
	    cout<<"\t "<<mat[i][j];
	}
     }
  };
void main()
 {
  int value;
  int m;
  clrscr();
  cout<<"Enter Matrix order=";cin>>m;
  valDet t1(m);
  clrscr();
  cout<<"You Have Enter The matrix=\n";
  t1.display();
  value=t1;
  cout<<"\n Value of Matrix= ";cout<<value;
  getch();
  }