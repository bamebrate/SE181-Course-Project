package banking;

public class CD extends Account {

	public CD(String id, double apr, double balance) {
		super(id, apr);
		this.balance = balance;
	}

	@Override
	public String getType() {
		return "banking.CD";
	}
}
