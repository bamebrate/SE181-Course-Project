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
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "Withdraw 12345678 300";
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
        bank.depositMoneyById("12345678", 1000);
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


    @Test
    void withdraw_1000_in_savings() {
        bank.addSavingsAccount("12345678", 0.63);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "withdraw 12345678 1000";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void withdraw_over_1000_in_savings() {
        bank.addSavingsAccount("12345678", 0.63);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "withdraw 12345678 1050";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void a_zero_value_for_withdraw_amount_in_checking() {
        bank.addCheckingAccount("12345678", 3.2);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "withdraw 12345678 0";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void withdrawing_negative_amount_into_checking_account() {
        bank.addCheckingAccount("12345678", 3.2);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "Withdraw 12345678 -100";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void withdraw_400_dollars_into_checking() {
        bank.addCheckingAccount("12345678", 3.2);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "Withdraw 12345678 400";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void withdraw_over_400_dollars_into_checking() {
        bank.addCheckingAccount("12345678", 3.2);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = "Withdraw 12345678 401";
        assertFalse(commandValidator.validate(commandAsString));
    }

    /**
     * ADD MORE TESTS FOR PASS TIME CD ACCOUNTS and pass time savings
     **/
    @Test
    void cd_account_younger_than_twelve_months_cant_make_a_withdrawal() {
        bank.addCDAccount("12345678", 0.1, 1000);
        bank.depositMoneyById("12345678", 1000);
        commandAsString = ("withdraw 12345678 100");
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_older_than_twelve_months_can_make_a_withdrawal() {
        bank.addCDAccount("12345678", 0.6, 5000);
        bank.passTime(12);
        commandAsString = ("withdraw 12345678 6000");
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_older_than_twelve_months_can_make_a_withdrawal_of_more_than_the_current_available_balance() {
        bank.addCDAccount("12345678", 0.6, 5000);
        bank.passTime(12);
        commandAsString = ("withdraw 12345678 6000.80");
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_older_than_twelve_months_can_not_make_a_withdrawal_of_less_than_the_current_available_balance() {
        bank.addCDAccount("12345678", 0.6, 5000);
        bank.passTime(12);
        commandAsString = ("withdraw 12345678 3000.80");
        assertFalse(commandValidator.validate(commandAsString));
    }


}