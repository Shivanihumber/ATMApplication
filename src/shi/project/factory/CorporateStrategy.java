package shi.project.factory;



public class CorporateStrategy implements Strategy{
	public static int ACTIV_FEE =20;
	
	public int getTransactionFee() {
		return 0;
	}
public int getNoActivityFee() {
	return ACTIV_FEE;
}
}
