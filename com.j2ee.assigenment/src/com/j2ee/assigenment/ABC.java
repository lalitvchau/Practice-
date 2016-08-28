package com.j2ee.assigenment;
public class ABC {
	 public static void main(String[] args) {
		int[] arr={1,12,5,111,200,1000,10,9,6,7,4};
		int num=50;
		int [] re=getMaximumToysWithMaxSaving(num, arr);
		for(int a:re){
			System.out.println(a);
		}
	}
	public static int[] getMaximumToysWithMaxSaving(int input1,int[] input2)
	{
	//Write code here
		int len=input2.length;
		  for(int i=0;i<len;i++){
	        	int min=i;
	        	for(int j=i+1;j<len;j++){
	        		if(input2[min]<input2[j]){
	        		
	        			min=j;
	        		}
	        	}
	        	int temp=input2[i];
	        	input2[i]=input2[min];
	        	input2[min]=temp;
	        }
	int resultArr[]=new int[2];
	resultArr[0]=0;
	resultArr[1]=0;
	int i=0;
	while(i<len){
	if(input1>=input2[i]){

	input1=input1-input2[i++];
	resultArr[1]=input1;
	resultArr[0]++;
	}
	else{
	i++;
	}
	}
	return resultArr;
	}
}
