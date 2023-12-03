package banking;

public class PassTimeCommandProcessor {
    private final Bank bank;

    public PassTimeCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void execute(String[] commandArr) {
        int month = Integer.parseInt(commandArr[1]);
        bank.passTime(month);
    }
}
