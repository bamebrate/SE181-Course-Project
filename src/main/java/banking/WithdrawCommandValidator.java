package banking;

public class WithdrawCommandValidator {
    private Bank bank;

    public WithdrawCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] commandArr) {
        if (commandArr.length == 3) {
            if (stringCommandHasCorrectID(commandArr) && stringCommandHasCorrectWithdrawAmount(commandArr)
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

    private boolean stringCommandHasCorrectWithdrawAmount(String[] commandArr) {
        double balance;
        Account account = bank.getAccounts().get(commandArr[1]);
        try {
            balance = Double.parseDouble(commandArr[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        if (account.getType() == "banking.Savings") {
            return (balance >= 0 && balance <= 1000);
        } else if (account.getType() == "banking.Checking") {
            return (balance >= 0 && balance <= 400);
        }
        return false;
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
