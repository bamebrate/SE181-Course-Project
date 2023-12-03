package banking;

public class PassTimeCommandValidator {
    private final Bank bank;

    public PassTimeCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] commandArr) {
        if (commandArr.length == 2) {
            if (stringCommandHasCorrectMonth(commandArr)) {
                return true;
            }
        }
        return false;
    }

    private boolean stringCommandHasCorrectMonth(String[] commandArr) {
        int month;
        try {
            month = Integer.parseInt(commandArr[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return (month > 0) && (month <= 61);

    }
}
