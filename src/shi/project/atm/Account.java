package shi.project.atm;

import java.time.LocalDate;

public interface Account {
	
	public void deposit(double amount);
	public void withdraw(double amount);
	public double getBalance();
	public boolean isCorporate();
	public void setLastActivityDate(LocalDate lastActivityDate);
	public LocalDate getLastActivityDate();

}
