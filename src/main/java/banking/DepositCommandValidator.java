package banking;

public class DepositCommandValidator {
	private final Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] commandArr) {
		if (commandArr.length == 3) {
			if (stringCommandHasCorrectID(commandArr) && stringCommandHasCorrectDepositAmount(commandArr)
					&& stringCommandAccountTypeIsNotCD(commandArr)) {
				return true;
			}
		}
		return false;
	}

	private boolean stringCommandAccountTypeIsNotCD(String[] commandArr) {
		Account account = bank.getAccounts().get(commandArr[1]);
		return !(account.getType() == "banking.CD");

	}

	private boolean stringCommandHasCorrectDepositAmount(String[] commandArr) {
		double balance;
		Account account = bank.getAccounts().get(commandArr[1]);
		try {
			balance = Double.parseDouble(commandArr[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (account.getType() == "banking.Savings") {
			return (balance >= 0 && balance <= 2500);
		} else {
			return (balance >= 0 && balance <= 1000);
		}
	}

	private boolean stringCommandHasCorrectID(String[] commandArr) {
		if (stringCommandIDExists(commandArr)) {
			return true;
		}
		return false;
	}

	private boolean stringCommandIDExists(String[] commandArr) {
		return bank.getAccounts().containsKey(commandArr[1]);
	}

}
