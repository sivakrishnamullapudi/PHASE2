package com.cg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Client {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String ans;
		WalletService service;
		{
			service = new WalletServiceImpl();
		}
		do {
			System.out.println("Enter your choice");
			System.out.println("1.Create Account");
			System.out.println("2.Display Balance");
			System.out.println("3.Fund Transfer");
			System.out.println("4.Deposit Ammount");
			System.out.println("5.Withdraw Amount");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Enter Your name");
				String name = sc.next();
				System.out.println("Enter Mobile Number");
				String num = sc.next();
				System.out.println("Enter Amount");
				BigDecimal amt = sc.nextBigDecimal();
				Customer cu = service.createAccount(name, num, amt);
				if(cu==null)
					throw new InvalidInputException("Invalid input");
				else
				System.out.println(cu);
				break;
			case 2:
				System.out.println("Pls Enter Your  mobile number");
				String mobileno = sc.next();
				Customer cu1 = service.showBalance(mobileno);
				System.out.println(cu1);
				break;
			case 3:
				System.out.println("Enter your mobile number");
				String srcMobNo = sc.next();
				System.out
						.println("Enter the recievers mobile number");
				String tarMobNo = sc.next();
				System.out.println("Enter the transfer amount");
				amt = sc.nextBigDecimal();
				Customer cu2 = service.fundTransfer(srcMobNo,
						tarMobNo, amt);
				System.out.println(cu2);
				break;
			case 4:
				System.out.println("Enter mobile number: ");
		   		mobileno=sc.next();
		   		System.out.println("Enter deposit amount: ");
		   		amt=sc.nextBigDecimal();
		   		try 
		   		{
		   			cu=service.depositAmount(mobileno, amt);
		   			System.out.println(cu);
		   		} 
		   		catch (Exception e) 
		   		{
		   			System.err.println(e.getMessage());
		   		}
		   		
		   		break;
			case 5:
				System.out.println("Enter mobile number: ");
		   		mobileno=sc.next();
		   		System.out.println("Enter withdrawal amount: ");
		   		amt=sc.nextBigDecimal();
		   		try
		   		{
		   			cu=service.withdrawAmount(mobileno, amt);
		   			System.out.println(cu);
		   		}
		   		catch (Exception e) 
		   		{
		   			System.err.println(e.getMessage());
		   		}
		   		
			}
			System.out.println("Do you want to continue");
			ans = sc.next();
		} while ((ans.equals("Yes")) | (ans.equals("y")) | (ans.equals("yes")));
	}
}
