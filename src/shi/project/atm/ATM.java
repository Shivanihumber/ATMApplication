package shi.project.atm;

public interface ATM {
	
	public boolean deposit(Account depositAccount,int hundreds,int fifties,int twenties);
	public boolean withdraw(Account withdrawalAccount,int amount);

}
