package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {

	private CommandValidator commandValidator;
	private Bank bank;
	private String commandAsString;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void validate_create_string() {
		commandAsString = "create savings 12345678 1.2";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void create_command_is_spelled_incorrectly() {
		commandAsString = "creete savings 12345678 1.2";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void create_in_all_caps_is_a_valid_command() {
		commandAsString = "CREATE savings 12345678 1.2";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void create_has_unexpected_numbers_which_makes_command_invalid() {
		commandAsString = "Create3 savings 12345678 1.2";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_command_is_valid() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 1000";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_is_different_caps() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "DePOsiT 12345678 1000";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_spelled_incorrectly() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "depostt 12345678 1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_has_unexpected_numbers_which_makes_command_invalid() {
		commandAsString = "Deposit3 savings 12345678 1.2";
		assertFalse(commandValidator.validate(commandAsString));
	}
}
