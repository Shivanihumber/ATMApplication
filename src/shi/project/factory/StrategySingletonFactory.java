package shi.project.factory;

import shi.project.atm.*;

public class StrategySingletonFactory {
	public static double PRIMIUM_MIN_BALANCE=5000.00;
	
	private static PremiumStrategy premiumStrategy = new PremiumStrategy();
	private static CorporateStrategy corporateStrategy = new CorporateStrategy();
	private static RegularStrategy RegularStrategy=new RegularStrategy();
	
	private StrategySingletonFactory() {
		
		
	}
	
	public static Strategy getInstance (Account account) {
		if(account.getBalance()>PRIMIUM_MIN_BALANCE) {
			return premiumStrategy;
		}else if (account.isCorporate()) {
			return corporateStrategy;
		}else {
			return RegularStrategy;
		}
	}

}
