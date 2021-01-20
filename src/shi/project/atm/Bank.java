package shi.project.atm;

import java.util.List;

public class Bank {
private String address;
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Account> getAccounts() {
	return accounts;
}
public void setAccounts(List<Account> accounts) {
	this.accounts = accounts;
}
public List<String> getCustomers() {
	return customers;
}
public void setCustomers(List<String> customers) {
	this.customers = customers;
}
private String name;
private List<Account>accounts;
private List<String>customers;


}
