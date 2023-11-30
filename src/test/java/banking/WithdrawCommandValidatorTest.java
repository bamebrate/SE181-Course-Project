package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidatorTest {
	private Bank bank;
	private String commandAsString;
	private CommandValidator commandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void withdrawing_from_an_existing_account() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "Withdraw 12345678 1000";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void withdrawing_a_nonexistant_account() {
		commandAsString = "Withdraw 12345678 1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

}