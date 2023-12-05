package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {
    private Bank bank;
    private String commandAsString;
    private CommandValidator commandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void transfer_from_checking_to_checking() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 100);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 100);
        commandAsString = "Transfer 00000001 00000002 50";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_checking_to_itself() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 100);
        commandAsString = "Transfer 00000001 00000001 50";
        assertFalse(commandValidator.validate(commandAsString));
    }

}
