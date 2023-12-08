package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandStorage {
    private final Bank bank;
    private List<String> invalidCommands = new ArrayList<>();
    private Map<String, List<String>> validCommands = new HashMap<>();


    public CommandStorage(Bank bank) {
        this.bank = bank;
    }

    public List<String> getOutput() {
        List<String> output = new ArrayList<>();
        for (String ID : bank.getAccountList()) {
            Account account = bank.getAccounts().get(ID);
            output.add(account.getAccountStatus(account));
            if (validCommands.get(ID) != null) {
                output.addAll(validCommands.get(ID));
            }
        }
        output.addAll(invalidCommands);

        return output;
    }


    public void storeValidCommand(String validCommand) {
        String[] commandArr = validCommand.split(" ");

        if (commandArr[0].equalsIgnoreCase("create") ||
                commandArr[0].equalsIgnoreCase("withdraw") ||
                commandArr[0].equalsIgnoreCase("deposit")) {
            addToMap(validCommands, commandArr[1], validCommand);
        } else if (commandArr[0].equalsIgnoreCase("transfer")) {
            addToMap(validCommands, commandArr[1], validCommand);
            addToMap(validCommands, commandArr[2], validCommand);
        }
    }

    private void addToMap(Map<String, List<String>> validCommandsList, String ID, String validCommand) {
        if (validCommandsList.get(ID) != null) {
            validCommandsList.get(ID).add(validCommand);
        } else if (validCommandsList.get(ID) == null) {
            validCommandsList.put(ID, new ArrayList<>());
            validCommandsList.get(ID).add(validCommand);
        }
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;
    }

    public void storeInvalidCommand(String invalidCommand) {
        invalidCommands.add(invalidCommand);
    }

    public Map<String, List<String>> getValidCommands() {
        return validCommands;
    }
}
