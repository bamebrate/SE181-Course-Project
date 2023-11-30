package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	private final Bank bank;
	private List<String> invalidCommands = new ArrayList<>();

	public CommandStorage(Bank bank) {
		this.bank = bank;
	}

	public List<String> getInvalidCommands() {
		return invalidCommands;
	}

	public void storeInvalidCommand(String invalidCommand) {
		invalidCommands.add(invalidCommand);
	}
}
