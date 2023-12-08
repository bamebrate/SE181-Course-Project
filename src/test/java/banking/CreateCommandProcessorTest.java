package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCommandProcessorTest {
    private String CommandAsString;
    private CommandProcessor commandProcessor;
    private Bank bank;

    @BeforeEach
    void setuo() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void create_checking_account() {
        CommandAsString = "create checking 12345678 0.01";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
        assertEquals(0.01, bank.getAccounts().get("12345678").getAPR());
        assertEquals("12345678", bank.getAccounts().get("12345678").getId());
    }

    @Test
    void create_savings_account() {
        CommandAsString = "create savings 12345678 0.01";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
        assertEquals(0.01, bank.getAccounts().get("12345678").getAPR());
        assertEquals("12345678", bank.getAccounts().get("12345678").getId());
    }

    @Test
    void create_CD_account() {
        CommandAsString = "create CD 12345678 0.01 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(1000, bank.getAccounts().get("12345678").getBalance());
        assertEquals(0.01, bank.getAccounts().get("12345678").getAPR());
        assertEquals("12345678", bank.getAccounts().get("12345678").getId());
    }
}
