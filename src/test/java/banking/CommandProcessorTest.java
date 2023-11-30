package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	private String CommandAsString;
	private CommandProcessor commandProcessor;
	private Bank bank;

	@BeforeEach
	void setuo() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void create_an_account_in_an_empty_bank() {
		CommandAsString = "create checking 12345678 0.01";
		commandProcessor.execute(CommandAsString);
		assertEquals(0, bank.getAccounts().get("12345678").getBalance());
		assertEquals(0.01, bank.getAccounts().get("12345678").getAPR());
		assertEquals("12345678", bank.getAccounts().get("12345678").getId());
	}

	@Test
	void deposit_into_empty_account() {
		bank.addCheckingAccount("12345678", 0.3);
		CommandAsString = "deposit 12345678 1000";
		commandProcessor.execute(CommandAsString);
		assertEquals(1000, bank.getAccounts().get("12345678").getBalance());
	}

}
