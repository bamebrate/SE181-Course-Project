package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCommandProcessorTest {

    private String CommandAsString;
    private CommandProcessor commandProcessor;
    private Bank bank;

    @BeforeEach
    void setuo() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void transfer_0_from_empty_checking_account() {
        bank.addCheckingAccount("12345678", 0.3);
        bank.addCheckingAccount("12345679", 0.3);
        CommandAsString = "transfer 12345678 12345679 0";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void transfer_100_from_checking_to_another_checking() {
        bank.addCheckingAccount("12345678", 0.3);
        bank.depositMoneyById("12345678", 1000);
        bank.addCheckingAccount("12345679", 0.3);
        CommandAsString = "transfer 12345678 12345679 100";
        commandProcessor.execute(CommandAsString);
        assertEquals(900, bank.getAccounts().get("12345678").getBalance());
        assertEquals(100, bank.getAccounts().get("12345679").getBalance());
    }

    @Test
    void transfer_100_from_savings_to_another_savings() {
        bank.addSavingsAccount("12345678", 0.3);
        bank.depositMoneyById("12345678", 1000);
        bank.addSavingsAccount("12345679", 0.3);
        CommandAsString = "transfer 12345678 12345679 100";
        commandProcessor.execute(CommandAsString);
        assertEquals(900, bank.getAccounts().get("12345678").getBalance());
        assertEquals(100, bank.getAccounts().get("12345679").getBalance());
    }

    @Test
    void transfer_300_from_a_savings_with_200_balance_to_another_savings() {
        bank.addSavingsAccount("12345678", 0.3);
        bank.depositMoneyById("12345678", 200);
        bank.addSavingsAccount("12345679", 0.3);
        CommandAsString = "transfer 12345678 12345679 300";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
        assertEquals(200, bank.getAccounts().get("12345679").getBalance());
    }

    @Test
    void transfer_twice_from_a_savings_with_1000_balance_to_another_savings() {
        bank.addSavingsAccount("12345678", 0.3);
        bank.depositMoneyById("12345678", 1000);
        bank.addSavingsAccount("12345679", 0.3);
        CommandAsString = "transfer 12345678 12345679 500";
        commandProcessor.execute(CommandAsString);
        CommandAsString = "transfer 12345678 12345679 600";
        commandProcessor.execute(CommandAsString);
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
        assertEquals(1000, bank.getAccounts().get("12345679").getBalance());
    }
}
