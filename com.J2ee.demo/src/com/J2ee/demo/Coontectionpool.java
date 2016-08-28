package com.J2ee.demo;

import javax.sql.CommonDataSource;

public class Coontectionpool {
   public static void main(String[] args) {
	   CommonDataSource cpds = new CommonDataSource();
	   ((Object) cpds).setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
	   cpds.setJdbcUrl( "jdbc:postgresql://localhost/testdb" );
	   cpds.setUser("swaldman");
	   cpds.setPassword("test-password");
}
}
