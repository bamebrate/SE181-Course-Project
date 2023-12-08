package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStorageTest {
    Bank bank;
    private CommandStorage commandStorage;

    @BeforeEach
    void setup() {
        bank = new Bank();
        commandStorage = new CommandStorage(bank);
    }

    @Test
    void storage_is_initially_empty() {
        assertEquals(0, commandStorage.getInvalidCommands().size());
    }

    @Test
    void storage_is_storing_one_invalid_command() {

        commandStorage.storeInvalidCommand("creete banking.Savings 12345678 0.2");
        assertEquals(1, commandStorage.getInvalidCommands().size());
        assertEquals("creete banking.Savings 12345678 0.2", commandStorage.getOutput().get(0));
    }

    @Test
    void storage_is_storing_two_invalid_command() {
        commandStorage.storeInvalidCommand("creete banking.Savings 12345678 0.2");
        commandStorage.storeInvalidCommand("create banking.Savings 123456789 0.2");
        assertEquals(2, commandStorage.getInvalidCommands().size());
    }

    @Test
    void storage_is_storing_the_correct_invalid_commands() {
        commandStorage.storeInvalidCommand("creete banking.Savings 12345678 0.2");
        commandStorage.storeInvalidCommand("create banking.Savings 123456789 0.2");
        assertEquals("creete banking.Savings 12345678 0.2", commandStorage.getInvalidCommands().get(0));
        assertEquals("create banking.Savings 123456789 0.2", commandStorage.getInvalidCommands().get(1));

    }

    @Test
    void store_two_valid_commands_for_an_ids_transaction_history() {
        bank.addSavingsAccount("12345678", 0.6);
        bank.addCheckingAccount("87654321", 0.6);
        commandStorage.storeValidCommand("Deposit 12345678 700");
        commandStorage.storeValidCommand("Transfer 87654321 12345678 300");
        assertEquals("Deposit 12345678 700", commandStorage.getValidCommands().get("12345678").get(0));
        assertEquals("Transfer 87654321 12345678 300", commandStorage.getValidCommands().get("12345678").get(1));
        assertEquals("Transfer 87654321 12345678 300", commandStorage.getValidCommands().get("87654321").get(0));
    }

    @Test
    void output_two_account_states() {
        bank.addSavingsAccount("12345678", 1.456);
        bank.depositMoneyById("12345678", 1000);
        bank.addCheckingAccount("98765432", 0.323);
        bank.depositMoneyById("98765432", 655.8888);
        assertEquals("Savings 12345678 1000.00 1.45", commandStorage.getOutput().get(0));
        assertEquals("Checking 98765432 655.88 0.32", commandStorage.getOutput().get(1));
    }


}
