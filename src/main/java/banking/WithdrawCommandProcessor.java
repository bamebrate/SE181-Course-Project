package banking;

public class WithdrawCommandProcessor {
    private Bank bank;

    public WithdrawCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void execute(String[] commandArr) {
        String accountID = commandArr[1];
        double amount = Double.parseDouble(commandArr[2]);
        bank.getAccounts().get(accountID).withdrawMoney(amount);
    }
}
