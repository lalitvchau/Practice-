package com.lalit.framework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Kuresh extends DBHandler {
	private int _roll = 107;
	private String name = "Sresh Kumar";



	public int get_roll() {
		return _roll;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		Kuresh s1=new Kuresh();
		
		System.out.println(s1.save());
	
		int k=100;
		 //System.out.println(s1.save());
		ArrayList rs = s1.getData("name","_roll>?",101);
		Iterator itr=rs.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}

	}



}
