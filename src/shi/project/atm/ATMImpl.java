package shi.project.atm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shi.project.factory.Strategy;
import shi.project.factory.StrategySingletonFactory;

public class ATMImpl implements ATM,ATMAdmin{

public static double TRANS_MIN_BALANCE = 3000.00;

private List<Denomination> currencyNotes = new ArrayList<>();

private Account account = new AccountImpl();

public ATMImpl() {
this(true);
}

public ATMImpl(boolean populateDefault) {
if (populateDefault) {
populateCurrency(100, 200, 200);
setDigitalCurrency(5000);
}
}

public boolean deposit(Account depositAccount, int hundreds, int fifties, int twenties) {
if (hundreds <= 0 || fifties <= 0 || twenties <= 0) {
System.out.println("Invalid amount");
return false;
}

for (int i = 0; i < hundreds; i++) {
currencyNotes.add(new HundredDollarNote());
}
for (int i = 0; i < fifties; i++) {
currencyNotes.add(new FiftyDollarNote());
}
for (int i = 0; i < twenties; i++) {
currencyNotes.add(new TwentyDollarNote());
}

account.withdraw((hundreds * 100) + (fifties * 50) + (twenties * 20));
depositAccount.deposit((hundreds * 100) + (fifties * 50) + (twenties * 20));

return true;
}

public boolean withdraw(Account withdrawalAccount, int amount) {
if (amount <= 0) {
System.out.println("Invalid amount");
return false;
}

if (withdrawalAccount == null) {
System.out.println("Account cannot be null");
return false;
}

if (!(amount % 20 == 0 || amount % 50 == 0)) {
System.out.println("Proper denomination not entered");
return false;
}

Strategy strategy = StrategySingletonFactory.getInstance(withdrawalAccount);
double balanceRequiredInAccount = strategy.getNoActivityFee()+ strategy.getTransactionFee() + amount;

if (withdrawalAccount.getBalance() < balanceRequiredInAccount) {
System.out.println("Low account Balance");
return false;
}

int hundredsRem = (checkHundredsCount() > (amount / 100)) ? amount % 100
: amount - (checkHundredsCount() * 100);
int fiftiesRem = (checkFiftiesCount() > (hundredsRem / 50)) ? hundredsRem % 50
: hundredsRem - (checkFiftiesCount() * 50);
int twentiesRem = (checkTwentiesCount() > (fiftiesRem / 20)) ? fiftiesRem % 20
: fiftiesRem - (checkTwentiesCount() * 20);

if (twentiesRem != 0) {
System.out.println("Currency combination not available to complete the transaction with exact amount");
return false;
}

if (dispenseTwenties(dispenseFifties(dispenseHundreds(amount))) != 0) {
throw new RuntimeException("unexpected error: balance of amount after dispening should be zero");
}

withdrawalAccount.withdraw(amount);
account.deposit(amount);

if (strategy.getNoActivityFee()> 0
&& withdrawalAccount.getLastActivityDate().plusYears(1).isBefore(LocalDate.now())) {
withdrawalAccount.withdraw(strategy.getNoActivityFee());
System.out.println("Charged InActivity Fee");
}
if (strategy.getTransactionFee() > 0) {
withdrawalAccount.withdraw(strategy.getTransactionFee());
System.out.println("Charged Transaction Fee");
}

return true;

}

private int checkHundredsCount() {
int count = 0;
for (Denomination note : currencyNotes) {
if (note instanceof HundredDollarNote)
count++;
}
return count;
}

private int checkFiftiesCount() {
int count = 0;
for (Denomination note : currencyNotes) {
if (note instanceof FiftyDollarNote)
count++;
}
return count;
}

private int checkTwentiesCount() {
int count = 0;
for (Denomination note : currencyNotes) {
if (note instanceof TwentyDollarNote)
count++;
}
return count;
}

private int dispenseHundreds(int amount) {
int toDispense = (checkHundredsCount() > (amount / 100)) ? amount / 100 : checkHundredsCount();
Iterator<Denomination> itt = currencyNotes.iterator();
for (int count = toDispense; itt.hasNext() && count > 0;) {
Denomination note = itt.next();
if (note instanceof HundredDollarNote) {
itt.remove();
count--;
System.out.println("Dispensed one Hundered Doller note");
}
}
return amount - (toDispense * 100);
}

private int dispenseFifties(int amount) {
int toDispense = (checkFiftiesCount() > (amount / 50)) ? amount / 50 : checkFiftiesCount();
Iterator<Denomination> itt = currencyNotes.iterator();
for (int count = toDispense; itt.hasNext() && count > 0;) {
Denomination note = itt.next();
if (note instanceof FiftyDollarNote) {
itt.remove();
count--;
System.out.println("Dispensed one Fifty Doller note");
}
}
return amount - (toDispense * 50);
}

private int dispenseTwenties(int amount) {
int toDispense = (checkTwentiesCount() > (amount / 20)) ? amount / 20 : checkTwentiesCount();
Iterator<Denomination> itt = currencyNotes.iterator();
for (int count = toDispense; itt.hasNext() && count > 0;) {
Denomination note = itt.next();

if (note instanceof TwentyDollarNote) {
itt.remove();
count--;
System.out.println("Dispensed one Twenty Doller note");
}
}
return amount - (toDispense * 20);
}

public int getCurrencyAmount() {
int value = 0;
for (Denomination note : currencyNotes) {
value = value + note.getValue();
}
return value;
}



public void setDigitalCurrency(int amount) {
account.deposit(amount);
}

public void populateCurrency(int hundreds, int fifties, int twenties) {
	// TODO Auto-generated method stub
	for (int i = 0; i < fifties; i++)
		currencyNotes.add(new FiftyDollarNote());
		for (int i = 0; i < twenties; i++)
		currencyNotes.add(new TwentyDollarNote());
		for (int i = 0; i < hundreds; i++)
		currencyNotes.add(new HundredDollarNote());
	
}




}
