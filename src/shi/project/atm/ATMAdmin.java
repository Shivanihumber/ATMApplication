package shi.project.atm;

public interface ATMAdmin {
	
	public int getCurrencyAmount();
	public void populateCurrency(int hundreds,int fifties,int twenties);
	public void setDigitalCurrency(int amount);

}
