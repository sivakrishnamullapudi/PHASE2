package com.cg.mypaymentapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sample {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		try{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","corp123");
			PreparedStatement st=con.prepareStatement("");
			st.execute();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}


}
