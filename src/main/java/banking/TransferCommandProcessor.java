package banking;

public class TransferCommandProcessor {
    private Bank bank;

    public TransferCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void execute(String[] commandArr) {
        String fromID = commandArr[1];
        String toID = commandArr[2];
        double amount = Double.parseDouble(commandArr[3]);
        if (amount > bank.getAccounts().get(fromID).getBalance()) {
            amount = bank.getAccounts().get(fromID).getBalance();
        }
        bank.transferMoneyById(fromID, toID, amount);
    }
}
