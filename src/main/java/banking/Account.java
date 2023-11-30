package banking;

public abstract class Account {
	public double balance = 0;
	private String id;
	private double apr;

	public Account(String id, double apr) {
		this.id = id;
		this.apr = apr;
	}

	public String getId() {
		return id;
	}

	public double getAPR() {
		return apr;
	}

	public double getBalance() {
		return balance;
	}

	public void depositMoney(double amount) {
		balance += amount;
	}

	public void withdrawMoney(double amount) {
		if (balance > amount) {
			balance -= amount;
		} else {
			balance = 0;
		}
	}

	public abstract String getType();

}
