package banking;

public class CommandProcessor {
    private final Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void execute(String commandAsString) {

        String[] commandArr = commandAsString.split(" ");
        if (commandArr[0].equalsIgnoreCase("create")) {
            CreateCommandProcessor commandProcessor = new CreateCommandProcessor(bank);
            commandProcessor.execute(commandArr);
        } else if (commandArr[0].equalsIgnoreCase("Deposit")) {
            DepositCommandProcessor commandProcessor = new DepositCommandProcessor(bank);
            commandProcessor.execute(commandArr);
        } else if (commandArr[0].equalsIgnoreCase("withdraw")) {
            WithdrawCommandProcessor commandProcessor = new WithdrawCommandProcessor(bank);
            commandProcessor.execute(commandArr);
        } else if (commandArr[0].equalsIgnoreCase("Pass")) {
            PassTimeCommandProcessor commandProcessor = new PassTimeCommandProcessor(bank);
            commandProcessor.execute(commandArr);
        }
    }
}
