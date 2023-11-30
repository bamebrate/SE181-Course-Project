package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {
	Bank bank;
	private CommandStorage commandStorage;

	@BeforeEach
	void setup() {
		bank = new Bank();
		commandStorage = new CommandStorage(bank);
	}

	@Test
	void storage_is_initially_empty() {
		assertEquals(0, commandStorage.getInvalidCommands().size());
	}

	@Test
	void storage_is_storing_one_invalid_command() {
		commandStorage.storeInvalidCommand("creete banking.Savings 12345678 0.2");
		assertEquals(1, commandStorage.getInvalidCommands().size());
	}

	@Test
	void storage_is_storing_two_invalid_command() {
		commandStorage.storeInvalidCommand("creete banking.Savings 12345678 0.2");
		commandStorage.storeInvalidCommand("create banking.Savings 123456789 0.2");
		assertEquals(2, commandStorage.getInvalidCommands().size());
	}

	@Test
	void storage_is_storing_the_correct_invalid_commands() {
		commandStorage.storeInvalidCommand("creete banking.Savings 12345678 0.2");
		commandStorage.storeInvalidCommand("create banking.Savings 123456789 0.2");
		assertEquals("creete banking.Savings 12345678 0.2", commandStorage.getInvalidCommands().get(0));
		assertEquals("create banking.Savings 123456789 0.2", commandStorage.getInvalidCommands().get(1));

	}

}
