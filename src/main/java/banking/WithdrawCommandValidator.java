package banking;

public class WithdrawCommandValidator {
    private Bank bank;

    public WithdrawCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] commandArr) {
        if (commandArr.length == 3) {
            if (stringCommandHasCorrectID(commandArr) && stringCommandHasCorrectWithdrawAmount(commandArr)
            ) {
                return true;
            }
        }
        return false;
    }


    private boolean stringCommandHasCorrectWithdrawAmount(String[] commandArr) {
        double amount;
        Account account = bank.getAccounts().get(commandArr[1]);
        int age = account.getAge();
        double balance = account.getBalance();


        try {
            amount = Double.parseDouble(commandArr[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        boolean canWithdraw = account.canWithdraw(balance, amount, age);
        return canWithdraw;

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
