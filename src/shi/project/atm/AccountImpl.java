package shi.project.atm;

import java.time.LocalDate;

public class AccountImpl implements Account{
	//Account has a builder design pattern. The builder will contain  all of the fields that exist on the
	//AccountImpl class itself.We will configure all of the fields that we want on the builder,and then we will 
	//use the builder to create accounts.
	
	
	private String accountNumber;
	private double balance;
	private String customer;
	private boolean corporate;
	private LocalDate lastActivityDate =  LocalDate.now();
	
	


	@Override
	public void deposit(double amount) {
		balance= balance+amount;
		
	}

	@Override
	public void withdraw(double amount) {
		balance=balance-amount;
		
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	@Override
	public boolean isCorporate() {
		// TODO Auto-generated method stub
		return corporate;
	}
	
	public void setCorporate(boolean corporate) {
		this.corporate=corporate;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public void setLastActivityDate(LocalDate lastActivityDate) {
	this.lastActivityDate=lastActivityDate;
		
	}

	@Override
	public LocalDate getLastActivityDate() {
		// TODO Auto-generated method stub
		return lastActivityDate;
	}
	
	public static class Builder{
		private ATMAdapter atm;
		private String accountNumber;
		private double balance;
		private String customer;
		private boolean corporate;
		private LocalDate lastActivityDate = LocalDate.now();
		
		
		public Builder (ATMAdapter atm) {
			this.atm=atm;
			
		
		}
		
		public Builder withAccountNumber(String accountNumber) {
			this.accountNumber=accountNumber;
			return this;//fluent design pattern return this
		}
		public Builder withCustomer (String customer) {
			this.customer=customer;
			return this;//fluent design pattern return this
		}
		public Builder havingBalance(double balance) {
			this.balance=balance;
			return this;
		}
		public Builder isCorporate(boolean corporate) {
			this.corporate=corporate;
			return this;
		}
		public Builder withLastActivityOn(LocalDate date) {
			this.lastActivityDate=date;
			return this;
		}
		
		public Account build() {
			AccountImpl account = new AccountImpl();
			atm.registerObserver(account,new AccountObserver(account));
			account.accountNumber=accountNumber;
			account.balance=balance;
			account.customer=customer;
			account.corporate=corporate;
			account.lastActivityDate=lastActivityDate;
			return account;
			
		}
	}
	
}
}
