package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<String, Account> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public Map<String, Account> getAccounts() {
		return accounts;
	}

	public void addSavingsAccount(String id, double apr) {
		accounts.put(id, new Savings(id, apr));
	}

	public void addCheckingAccount(String id, double apr) {
		accounts.put(id, new Checking(id, apr));
	}

	public void addCDAccount(String id, double apr, double balance) {
		accounts.put(id, new CD(id, apr, balance));
	}

	public void depositMoneyById(String id, int amount) {
		accounts.get(id).depositMoney(amount);
	}

	public void withdrawMoneyById(String id, int amount) {
		accounts.get(id).withdrawMoney(amount);
	}
}
