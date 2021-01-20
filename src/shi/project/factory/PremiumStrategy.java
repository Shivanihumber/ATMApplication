package shi.project.factory;

public class PremiumStrategy implements Strategy {

	public int getTransactionFee() {
		return 0;
	}
	
	public int getNoActivityFee() {
		return 0;
	}
}
