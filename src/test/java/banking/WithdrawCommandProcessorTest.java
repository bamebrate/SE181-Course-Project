package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawCommandProcessorTest {

    private String CommandAsString;
    private CommandProcessor commandProcessor;
    private Bank bank;

    @BeforeEach
    void setuo() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void withdraw_from_empty_checking_account() {
        bank.addCheckingAccount("12345678", 0.3);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void withdraw_from_empty_checking_account_twice() {
        bank.addCheckingAccount("12345678", 0.3);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void withdraw_from_checking_account_twice() {
        bank.addCheckingAccount("12345678", 0.3);
        bank.depositMoneyById("12345678", 2500);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(500, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void wiithdraw_from_empty_savings_account() {
        bank.addSavingsAccount("12345678", 0.3);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void withdraw_from_empty_savings_account_twice() {
        bank.addSavingsAccount("12345678", 0.3);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void withdraw_from_savings_account_twice() {
        bank.addSavingsAccount("12345678", 0.3);
        bank.depositMoneyById("12345678", 2500);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        CommandAsString = "withdraw 12345678 1000";
        commandProcessor.execute(CommandAsString);
        assertEquals(500, bank.getAccounts().get("12345678").getBalance());
    }
}
