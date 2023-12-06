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
    void transferring_to_a_nonexistent_account() {
        bank.addSavingsAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        commandAsString = "Transfer 00000001 00000002 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_amount_and_to_and_from_ID() {
        bank.addCheckingAccount("12345678", 3.2);
        commandAsString = "Transfer";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_every_command_or_is_empty() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = " ";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_transfer_amount() {
        bank.addCheckingAccount("12345678", 3.2);
        bank.addCheckingAccount("12345679", 3.2);
        commandAsString = "transfer 12345678 12345679";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_the_transfer_command() {
        bank.addSavingsAccount("12345678", 3.2);
        bank.addSavingsAccount("12345679", 3.2);
        commandAsString = "12345679 12345678 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_8_digits_for_transferring_to() {
        bank.addSavingsAccount("12345678", 3.2);
        bank.addSavingsAccount("12345679", 3.2);
        commandAsString = "transfer 12345678 123456789 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void non_numerical_value_for_ID_of_account() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "Transfer 1a345678 12345678 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void negative_value_for_transfer_amount() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 1000);
        commandAsString = "Transfer 00000001 00000002 -400";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void a_zero_value_for_transfer_amount_in_savings() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 1000);
        commandAsString = "Transfer 00000001 00000002 0";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void invalid_transfer_amount_type() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 1000);
        commandAsString = "Transfer 00000001 00000002 abcd";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_amount_has_unexpected_symbol() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 1000);
        commandAsString = "Transfer 00000001 00000002 4,00";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_checking_to_checking() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 1000);
        commandAsString = "Transfer 00000001 00000002 400";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_savings_to_savings() {
        bank.addSavingsAccount("00000001", 3);
        bank.depositMoneyById("00000001", 1000);
        bank.addCheckingAccount("00000002", 4);
        bank.depositMoneyById("00000002", 1000);
        commandAsString = "Transfer 00000001 00000002 400";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_checking_to_savings() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 100);
        bank.addSavingsAccount("00000002", 4);
        bank.depositMoneyById("00000002", 100);
        commandAsString = "Transfer 00000001 00000002 400";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_savings_to_checking() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 100);
        bank.addSavingsAccount("00000002", 4);
        bank.depositMoneyById("00000002", 100);
        commandAsString = "Transfer 00000002 00000001 50";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_checking_to_itself() {
        bank.addCheckingAccount("00000001", 3);
        bank.depositMoneyById("00000001", 100);
        commandAsString = "Transfer 00000001 00000001 50";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void transfer_from_savings_to_itself() {
        bank.addSavingsAccount("00000001", 3);
        bank.depositMoneyById("00000001", 100);
        commandAsString = "Transfer 00000001 00000001 50";
        assertFalse(commandValidator.validate(commandAsString));
    }


}
