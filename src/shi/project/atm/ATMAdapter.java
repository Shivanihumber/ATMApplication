package shi.project.atm;

import java.util.HashMap;
import java.util.Map;

public class ATMAdapter implements ATM{

	private ATM atm;
	
	public ATMAdapter() {
		atm=new ATMImpl();
	}
	
	public ATMAdapter(boolean initMoney) {
		atm=new ATMImpl(initMoney);
	}
	
	private Map<Account,Observer>observers = new HashMap<>();
	
	public ATMAdmin getAdminInterface() {
		return (ATMAdmin )atm;
		
	}
	
	public void registerObserver(Account account,Observer observer) {
		observers.put(account,observer);
	}
	
	@Override
	public boolean deposit(Account depositAccount,int hundred,int fifties,int twenties) {
		boolean success = atm.deposit(depositAccount, hundred, fifties, twenties);
		if(success) {
			observers.get(depositAccount).update();
		}
		return success;
	}

	@Override
	public boolean withdraw(Account withdrawalAccount, int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
