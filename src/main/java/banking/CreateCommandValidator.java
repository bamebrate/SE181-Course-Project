package banking;

public class CreateCommandValidator {

    private final Bank bank;

    public CreateCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] commandArr) {
        if (isCheckingOrSavings(commandArr) && commandArr.length == 4) {
            if (stringCommandHasCorrectID(commandArr) && stringCommandHasCorrectAPR(commandArr)) {
                return true;
            }
        } else if (isCDAccount(commandArr) && commandArr.length == 5) {
            if (stringCommandHasCorrectID(commandArr) && stringCommandHasCorrectAPR(commandArr)
                    && stringCommandhasCorrectBalance(commandArr)) {
                return true;
            }
        }
        return false;
    }

    private boolean stringCommandhasCorrectBalance(String[] commandArr) {
        double balance;
        try {
            balance = Double.parseDouble(commandArr[4]);
        } catch (NumberFormatException e) {
            return false;
        }
        return (balance >= 1000 && balance <= 10000);

    }

    private boolean isCDAccount(String[] commandArr) {
        return commandArr[1].equalsIgnoreCase("banking.cd");

    }

    private boolean isCheckingOrSavings(String[] commandArr) {

        return commandArr[1].equalsIgnoreCase("checking") || commandArr[1].equalsIgnoreCase("savings");

    }

    private boolean stringCommandHasCorrectID(String[] commandArr) {
        if (stringCommandHasUniqueID(commandArr) && stringCommandHas8digitID(commandArr)
                && stringCommandHasNumberID(commandArr)) {
            return true;
        }
        return false;
    }

    private boolean stringCommandHasNumberID(String[] commandArr) {
        double ID;
        try {
            ID = Double.parseDouble(commandArr[2]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private boolean stringCommandHas8digitID(String[] commandArr) {
        if (commandArr[2].length() == 8) {
            return true;
        }
        return false;
    }

    private boolean stringCommandHasCorrectAPR(String[] commandArr) {
        double apr;
        try {
            apr = Double.parseDouble(commandArr[3]);
        } catch (NumberFormatException e) {
            return false;
        }
        return (apr >= 0 && apr <= 10);
    }

    public boolean stringCommandHasUniqueID(String[] commandArr) {
        return !bank.getAccounts().containsKey(commandArr[2]);
    }

}
