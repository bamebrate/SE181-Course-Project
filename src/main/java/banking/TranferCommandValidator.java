package banking;

public class TranferCommandValidator {
    private final Bank bank;

    public TranferCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] commandArr) {
        Account fromAccount = bank.getAccounts().get(commandArr[1]);
        Account toAccount = bank.getAccounts().get(commandArr[2]);
        if (!commandArr[1].equals(commandArr[2])) {
            return toAccount.canTransfer() && fromAccount.canTransfer();
        } else {
            return false;
        }
    }

}
