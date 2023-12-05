package banking;

public class CommandValidator {
    private final Bank bank;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String commandAsString) {
        try {
            String[] commandArr = commandAsString.split(" ");
            if (commandArr[0].equalsIgnoreCase("create")) {
                CreateCommandValidator validator = new CreateCommandValidator(bank);
                return validator.validate(commandArr);
            } else if (commandArr[0].equalsIgnoreCase("deposit")) {
                DepositCommandValidator validator = new DepositCommandValidator(bank);
                return validator.validate(commandArr);
            } else if (commandArr[0].equalsIgnoreCase("withdraw")) {
                WithdrawCommandValidator validator = new WithdrawCommandValidator(bank);
                return validator.validate(commandArr);
            } else if (commandArr[0].equalsIgnoreCase("pass")) {
                PassTimeCommandValidator validator = new PassTimeCommandValidator(bank);
                return validator.validate(commandArr);
            } else if (commandArr[0].equalsIgnoreCase("Transfer")) {
                TranferCommandValidator validator = new TranferCommandValidator(bank);
                return validator.validate(commandArr);
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

}
