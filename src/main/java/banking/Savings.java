package banking;

public class Savings extends Account {

	public Savings(String id, double apr) {
		super(id, apr);
	}

	@Override
	public String getType() {
		return "banking.Savings";
	}

}
