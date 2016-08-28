package com.lalit.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ASlamBook extends DBHandler {
	private int _id = 3;
	private String name = "ASrav";
	public float getRate() {
		return rate;
	}

	public short getK() {
		return k;
	}

	private String lastName = "Kumar";
	private long num = 8892021052l;
	private double sal = 2002.00;
	private float rate = 11.1f;
	private short k=1;

	public static void main(String[] args) {
		ASlamBook book1 = new ASlamBook();
		System.out.println(book1.save());
		ArrayList rs = book1.getData();
		Iterator itr=rs.iterator();
		while(itr.hasNext()){
			HashMap map1=(HashMap) itr.next();
			int ik= (int) map1.get("k");
			book1.k=(short) ik;	
			book1.lastName=map1.get("lastName").toString();
			System.out.println("K= "+book1.k);
			System.out.println("nam= "+book1.lastName);
		}
	}

	public int get_id() {
		return _id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public long getNum() {
		return num;
	}

	public double getSal() {
		return sal;
	}



	public void setK(short k) {
		this.k = k;
	}


}
