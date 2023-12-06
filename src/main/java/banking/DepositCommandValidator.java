package banking;

public class DepositCommandValidator {
    private final Bank bank;

    public DepositCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] commandArr) {
        if (commandArr.length == 3) {
            if (stringCommandHasCorrectID(commandArr) && stringCommandHasCorrectDepositAmount(commandArr)) {
                return true;
            }
        }
        return false;
    }


    private boolean stringCommandHasCorrectDepositAmount(String[] commandArr) {
        double amount;
        Account account = bank.getAccounts().get(commandArr[1]);
        try {
            amount = Double.parseDouble(commandArr[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        boolean canDeposit = account.canDeposit(amount);
        return canDeposit;
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
