package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {

	private MasterControl masterControl;
	private List<String> command;

	@BeforeEach
	void setup() {
		command = new ArrayList<>();
		Bank bank = new Bank();
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank),
				new CommandStorage(bank));
	}

	@Test
	void typo_in_create_command_is_invalid() {
		command.add("creat checking 12345678 1.0");
		List<String> actual = masterControl.start(command);
		assertSingleCommand("creat checking 12345678 1.0", actual);
	}

	@Test
	void typo_in_deposit_command_is_invalid() {
		command.add("deposittt 12345678 100");
		List<String> actual = masterControl.start(command);
		assertSingleCommand("deposittt 12345678 100", actual);

	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void two_typo_commands_both_invalid() {
		command.add("creat checking 12345678 1.0");
		command.add("deposittt 12345678 100");

		List<String> actual = masterControl.start(command);

		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));
		assertEquals("deposittt 12345678 100", actual.get(1));

	}

	@Test
	void invalid_to_create_accounts_with_same_ID() {
		command.add("create checking 12345678 1.0");
		command.add("create checking 12345678 1.0");

		List<String> actual = masterControl.start(command);

		assertSingleCommand("create checking 12345678 1.0", actual);

	}

}