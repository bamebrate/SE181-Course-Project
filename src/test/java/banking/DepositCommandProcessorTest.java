package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandProcessorTest {
	private String CommandAsString;
	private CommandProcessor commandProcessor;
	private Bank bank;

	@BeforeEach
	void setuo() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void deposit_into_empty_checking_account() {
		bank.addCheckingAccount("12345678", 0.3);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		assertEquals(1000, bank.getAccounts().get("12345678").getBalance());
	}

	@Test
	void deposit_into_empty_checking_account_twice() {
		bank.addCheckingAccount("12345678", 0.3);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		assertEquals(2000, bank.getAccounts().get("12345678").getBalance());
	}

	@Test
	void deposit_into_empty_savings_account() {
		bank.addSavingsAccount("12345678", 0.3);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		assertEquals(1000, bank.getAccounts().get("12345678").getBalance());
	}

	@Test
	void deposit_into_empty_savings_account_twice() {
		bank.addSavingsAccount("12345678", 0.3);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		assertEquals(2000, bank.getAccounts().get("12345678").getBalance());
	}

}
