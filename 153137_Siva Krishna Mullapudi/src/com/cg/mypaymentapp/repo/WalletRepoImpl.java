package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.util.Sample;

public class WalletRepoImpl implements WalletRepo{

	
	
	public WalletRepoImpl(Map<String, Customer> data) 
	{
		
	}

	public boolean save(Customer customer) throws Exception 
	{
		if(findOne(customer.getMobileNo())==null)
		{
		try{	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","corp123");
			String sql="insert into Customer values(?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,customer.getMobileNo());
			stmt.setString(2,customer.getName());
			stmt.setInt(3,customer.getWallet().getBalance().intValueExact());
			stmt.executeUpdate();
			con.close();
		return true;}
		catch(Exception e)
		{
			return false;
			
		}}
		
		else
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","corp123");
			String sql="update  Customer set amount= ? "+"where mobilenumber= ? "+";";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1,customer.getWallet().getBalance().intValueExact() );
			stmt.setString(2,customer.getMobileNo());
			stmt.executeUpdate();
			con.close();
			return true;
		}
	}
	
	

	public Customer findOne(String mobileNo) 
	{
		
		try
		{	Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","corp123");
		String sql="select * from Customer where mobilenumber="+mobileNo+";";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		Customer c=null;
		while(rs.next())
		{c=new Customer(rs.getString(2),rs.getString(1),new Wallet(new BigDecimal(rs.getInt(3))));
		}
		return c;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
}
