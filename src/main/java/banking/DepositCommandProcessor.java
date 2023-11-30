package banking;

public class DepositCommandProcessor {
	private final Bank bank;

	public DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void execute(String[] commandArr) {
		String accountID = commandArr[1];
		double amount = Double.parseDouble(commandArr[2]);
		bank.getAccounts().get(accountID).depositMoney(amount);
	}

}
