package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandValidatorTest {
    private Bank bank;
    private String commandAsString;
    private CommandValidator commandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void withdrawing_from_an_existing_account() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "Withdraw 12345678 1000";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void withdrawing_a_nonexistant_account() {
        commandAsString = "Withdraw 12345678 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_withdraw_amount_and_ID() {
        bank.addCheckingAccount("12345678", 3.2);
        commandAsString = "withdraw";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_every_command_or_is_empty() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = " ";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_withdraw_amount() {
        bank.addCheckingAccount("12345678", 3.2);
        commandAsString = "withdraw 12345678";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void missing_the_withdraw_command() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "12345678 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_8_digits_for_withdrawing() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "withdraw 123456789 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void non_numerical_value_for_ID_of_account() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "Withdraw 1a345678 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void negative_value_for_withdraw_amount() {
        bank.addCheckingAccount("12345678", 3.2);
        commandAsString = "withdraw 12345678 -1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void a_zero_value_for_withdraw_amount_in_savings() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "withdraw 12345678 0";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void invalid_withdraw_amount_type() {
        bank.addCheckingAccount("12345678", 3.2);
        commandAsString = "Withdraw 12345678 abcd";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void withdraw_amount_has_unexpected_symbol() {
        bank.addSavingsAccount("12345678", 0.01);
        commandAsString = ("withdraw 12345678 1,000");
        assertFalse(commandValidator.validate(commandAsString));
    }
}