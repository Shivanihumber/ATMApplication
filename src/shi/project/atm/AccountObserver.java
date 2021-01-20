package shi.project.atm;

import java.time.LocalDate;

public class AccountObserver implements Observer{
	
	private Account account;
	public AccountObserver(Account account) {
		this.account=account;
	}
	
	@Override
	public void update() {
		account.setLastActivityDate(LocalDate.now());
	}
	
	

}
