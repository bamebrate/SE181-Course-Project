package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeCommandProcessorTest {
    private Bank bank;
    private CommandProcessor commandProcessor;
    private String CommandAsString;

    @BeforeEach
    void setuo() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void pass_1_month_on_checking_account() {
        bank.addCheckingAccount("12345678", 3);
        bank.depositMoneyById("12345678", 1000);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(1002.5, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void pass_1_month_on_savings_account() {
        bank.addCheckingAccount("12345678", 0.6);
        bank.depositMoneyById("12345678", 5000);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(5002.5, bank.getAccounts().get("12345678").getBalance());
    }
}


