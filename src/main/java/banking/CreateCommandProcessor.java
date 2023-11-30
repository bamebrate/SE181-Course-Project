package banking;

public class CreateCommandProcessor {
	private final Bank bank;

	public CreateCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void execute(String[] commandArr) {
		String type = commandArr[1];
		String id = commandArr[2];
		double apr = Double.parseDouble(commandArr[3]);

		if (type.equalsIgnoreCase("checking")) {
			bank.addCheckingAccount(id, apr);
		} else if (type.equalsIgnoreCase("savings")) {
			bank.addSavingsAccount(id, apr);
		} else if (type.equalsIgnoreCase("banking.cd")) {
			double balance = Double.parseDouble(commandArr[4]);
			bank.addCDAccount(id, apr, balance);
		}
	}
}