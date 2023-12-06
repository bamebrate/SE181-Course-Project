package banking;

public class TranferCommandValidator {
    private final Bank bank;
    private final CommandValidator validator;

    public TranferCommandValidator(Bank bank) {
        this.bank = bank;
        validator = new CommandValidator(bank);
    }

    public boolean validate(String[] commandArr) {
        if (commandArr.length == 4) {
            if (!commandArr[1].equals(commandArr[2])) {
                if (withdrawIsValid(commandArr) && depositIsValid(commandArr)) {
                    Account fromAccount = bank.getAccounts().get(commandArr[1]);
                    Account toAccount = bank.getAccounts().get(commandArr[2]);
                    return toAccount.canTransfer() && fromAccount.canTransfer();
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean depositIsValid(String[] commandArr) {
        String toAccount = commandArr[2];
        String amount = commandArr[3];
        String depositCommand = "deposit " + toAccount + " " + amount;

        return validator.validate(depositCommand);
    }

    private boolean withdrawIsValid(String[] commandArr) {
        String fromAccount = commandArr[1];
        String amount = commandArr[3];
        String withdrawalCommand = "withdraw " + fromAccount + " " + amount;
        return validator.validate(withdrawalCommand);
    }

}
