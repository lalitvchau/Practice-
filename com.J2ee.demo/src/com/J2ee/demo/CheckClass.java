package com.J2ee.demo;

public class CheckClass {
   public static boolean isString(String str){
	   
	  
	   
	   if(isNull(str)){
		   return false;
	   }  
	   
	   int len=str.length();
	   while(--len>=0){
		   
		   if(!((str.charAt(len)>=65&&str.charAt(len)<=90)||(str.charAt(len)>=97&&str.charAt(len)<=122))){
			   return false;
		   }
	   }
	  
	   return true;
   }
public static boolean isNull(String str){
	   if(str==null){
		   return true;
	   }
	   int len=str.length();
	   if(len<=0){
		   return true;
	   }
	   return false;
   }
   public static void main(String[] args) {
	    String s1=null;
	    System.out.println(isString(s1));
  }
}
