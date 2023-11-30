package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {
	private Bank bank;
	private String commandAsString;
	private CommandValidator commandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void depositing_to_an_existing_account() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "DePOsiT 12345678 1000";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void depositing_to_a_nonexistant_account() {
		commandAsString = "DePOsiT 12345678 1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void missing_deposit_amount_and_ID() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "deposit";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void missing_every_command_or_is_empty() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = " ";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void missing_deposit_amount() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "deposit 12345678";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void missing_the_deposit_command() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "12345678 1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void ID_is_not_8_digits_for_depositing() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "deposit 123456789 1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void non_numerical_value_for_ID_of_account() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "Deposit 1a345678 1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void negative_value_for_deposit_amount() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 -1000";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void a_zero_value_for_deposit_amount_in_savings() {
		bank.addSavingsAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 0";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void invalid_deposit_amount_type() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 abcd";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_amount_has_unexpected_symbol() {
		bank.addSavingsAccount("12345678", 0.01);
		commandAsString = ("deposit 12345678 1,000");
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void cd_account_cant_make_a_deposit() {
		bank.addCDAccount("12345678", 0.1, 1000);
		commandAsString = ("deposit 12345678 100");
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_2500_in_savings() {
		bank.addSavingsAccount("12345678", 0.63);
		commandAsString = "deposit 12345678 2500";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_over_2500_in_savings() {
		bank.addSavingsAccount("12345678", 0.63);
		commandAsString = "deposit 12345678 2550";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void a_zero_value_for_deposit_amount_in_checkings() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 0";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void depositing_negative_amount_into_checking_account() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 -100";
		assertFalse(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_1000_dollars_into_checking() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 1000";
		assertTrue(commandValidator.validate(commandAsString));
	}

	@Test
	void deposit_over_1000_dollars_into_checking() {
		bank.addCheckingAccount("12345678", 3.2);
		commandAsString = "Deposit 12345678 2000";
		assertFalse(commandValidator.validate(commandAsString));
	}

}
