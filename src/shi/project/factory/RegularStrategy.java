package shi.project.factory;

public class RegularStrategy implements Strategy{
	public static int TRANS_FEE=10, ACTIVE_FEE=20;
	public int getTransactionFee() {
		return TRANS_FEE;
	}
	
	public int getNoActivityFee() {
		return ACTIVE_FEE;
	}

}
