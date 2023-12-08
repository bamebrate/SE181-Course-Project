package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassTimeCommandProcessorTest {
    private Bank bank;
    private CommandProcessor commandProcessor;
    private String CommandAsString;

    @BeforeEach
    void setup() {
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

    @Test
    void pass_1_month_on_cd_account() {
        bank.addCDAccount("12345678", 0.6, 5000);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(5010.01, Math.round(bank.getAccounts().get("12345678").getBalance() * 100.0) / 100.0);
    }

    @Test
    void pass_1_month_on_checking_with_0_balance() {
        bank.addCheckingAccount("12345678", 3);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertNull(bank.getAccounts().get("12345678"));
    }

    @Test
    void pass_1_month_on_checking_with_less_than_100_balance() {
        bank.addCheckingAccount("12345678", 3);
        bank.depositMoneyById("12345678", 90);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(65.1625, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void pass_1_month_on_checking_with_greater_than_100_balance() {
        bank.addCheckingAccount("12345678", 3);
        bank.depositMoneyById("12345678", 1000);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(1002.5, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void pass_1_month_on_CD_account_with_greater_than_100_balance() {
        bank.addCDAccount("12345678", 2.1, 2000);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(2014.04, Math.round(bank.getAccounts().get("12345678").getBalance() * 100.0) / 100.0);
    }


    @Test
    void pass_twice_on_checking_account() {
        bank.addCheckingAccount("12345678", 1);
        bank.depositMoneyById("12345678", 1000);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        CommandAsString = "pass 1";
        commandProcessor.execute(CommandAsString);
        assertEquals(1001.67, Math.round(bank.getAccounts().get("12345678").getBalance() * 100.0) / 100.0);
    }

}


