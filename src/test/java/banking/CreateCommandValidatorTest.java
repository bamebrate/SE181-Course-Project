package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {
    private Bank bank;
    private String commandAsString;
    private CommandValidator commandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void missing_every_command_or_is_empty() {
        commandAsString = " ";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_and_Apr_and_account_type_are_not_provided() {
        commandAsString = "create";
        assertFalse(commandValidator.validate(commandAsString));
    }

    /**** Tests for banking.Savings account ****/

    @Test
    void creating_a_savings_account_with_a_duplicate_ID() {
        bank.addSavingsAccount("12345678", 3.2);
        commandAsString = "create savings 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void savings_is_spelled_incorrectly() {
        commandAsString = "create savingss 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void string_is_missing_create_command_for_savings() {
        commandAsString = "savings 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void string_is_missing_savings_command() {
        commandAsString = "create 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void Apr_is_more_than_zero_for_savings() {
        commandAsString = "create savings 12345678 11.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void Apr_is_less_than_range_for_savings() {
        commandAsString = "create savings 12345678 -1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_8_digits_for_savings() {
        commandAsString = "create savings 123456789 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_not_provided_for_savings() {
        commandAsString = "create savings 12345678";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void can_create_an_account_with_zero_apr_for_savings() {
        commandAsString = "create savings 12345678 0";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void can_create_an_account_with_max_ten_percent_for_savings() {
        commandAsString = "create savings 12345678 10";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_not_double_for_savings() {
        commandAsString = "create savings 12345678 b.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_numbers_for_savings() {
        commandAsString = "create savings 1234abcd 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_provided_for_savings() {
        commandAsString = "create savings 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_and_Apr_is_not_provided_for_savings() {
        commandAsString = "create savings";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_within_range_for_savings() {
        commandAsString = "create savings 12345678 1.2";
        assertTrue(commandValidator.validate(commandAsString));

    }

    @Test
    void savings_is_in_all_caps() {
        commandAsString = "create SAVINGS 12345678 1.2";
        assertTrue(commandValidator.validate(commandAsString));
    }

    /**** Tests for banking.Checking account ****/

    @Test
    void creating_a_valid_checking_account() {
        commandAsString = "create checking 12345678 1.2";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void checking_is_spelled_incorrectly() {
        commandAsString = "create ccheccking 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void checking_is_in_all_caps() {
        commandAsString = "create CHECKING 12345678 1.2";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void checking_command_is_missing() {
        commandAsString = "create 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void creating_a_checking_account_with_a_duplicate_ID() {
        bank.addCheckingAccount("12345678", 3.2);
        commandAsString = "create checking 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void Apr_is_more_than_range_for_checking() {
        commandAsString = "create checking 12345678 11.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void Apr_is_less_than_range_for_checking() {
        commandAsString = "create checking 12345678 -1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void can_create_an_account_with_zero_apr_for_checking() {
        commandAsString = "create checking 12345678 0";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void can_create_an_account_with_max_ten_percent_for_checking() {
        commandAsString = "create checking 12345678 10";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_8_digits_for_checking() {
        commandAsString = "create checking 123456789 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_not_provided_for_checking() {
        commandAsString = "create checking 12345678";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_not_double_for_checking() {
        commandAsString = "create checking 12345678 b.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_numbers_for_checking() {
        commandAsString = "create checking 1234abcd 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_provided_for_checking() {
        commandAsString = "create checking 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_and_Apr_is_not_provided_for_checking() {
        commandAsString = "create checking";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_within_range_for_checking() {
        commandAsString = "create checking 12345678 1.2";
        assertTrue(commandValidator.validate(commandAsString));

    }

    /**** Tests for banking.CD account ****/

    @Test
    void creating_a_valid_CD_account() {
        commandAsString = "create CD 12345678 1.2 1000";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_is_spelled_incorrectly() {
        commandAsString = "create CDD 12345678 1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_command_is_missing() {
        commandAsString = "create 12345678 1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void creating_a_cd_account_with_a_duplicate_ID() {
        bank.addCDAccount("12345678", 3.2, 1000);
        commandAsString = "create CD 12345678 1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void Apr_is_more_than_range_for_cd() {
        commandAsString = "create CD 12345678 11.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void Apr_is_less_than_range_for_cd() {
        commandAsString = "create CD 12345678 -1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_8_digits_for_cd() {
        commandAsString = "create CD 123456789 1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_not_provided_for_cd() {
        commandAsString = "create CD 12345678 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_not_double_for_cd() {
        commandAsString = "create CD 12345678 b.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_numbers_for_cd() {
        commandAsString = "create CD 1234abcd 1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_is_not_provided_for_cd() {
        commandAsString = "create CD 1.2 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_and_Apr_is_not_provided_for_cd() {
        commandAsString = "create CD 1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void ID_and_Apr_and_balance_are_not_provided_for_cd() {
        commandAsString = "create CD";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void APR_is_within_range_for_cd() {
        commandAsString = "create CD 12345678 1.2 1000";
        assertTrue(commandValidator.validate(commandAsString));

    }

    @Test
    void balance_not_specified_for_CD_account() {
        commandAsString = "create CD 12345678 1.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void balance_is_invalid_type() {
        commandAsString = "create CD 12345678 1.2 10cd";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void balance_is_negative_value() {
        commandAsString = "create CD 12345678 1.2 -1000";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_balance_is_1000() {
        commandAsString = "create CD 12345678 1.2 1000";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_balance_is_under_1000() {
        commandAsString = "create CD 12345678 1.2 500";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_balance_is_10000() {
        commandAsString = "create CD 12345678 1.2 10000";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void cd_account_balance_is_over_10000() {
        commandAsString = "create CD 12345678 1.2 0";
        assertFalse(commandValidator.validate(commandAsString));
    }

}
