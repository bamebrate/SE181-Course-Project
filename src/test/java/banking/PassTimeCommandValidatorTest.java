package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeCommandValidatorTest {

    private CommandValidator commandValidator;
    private String commandAsString;

    @BeforeEach
    void setup() {
        Bank bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void command_has_no_commands() {
        commandAsString = "";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void command_has_no_month() {
        commandAsString = "pass ";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_within_correct_range() {
        commandAsString = "pass 12";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_less_than_correct_range() {
        commandAsString = "pass 0";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_greater_than_correct_range() {
        commandAsString = "pass 62";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_61() {
        commandAsString = "pass 61";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_1() {
        commandAsString = "pass 1";
        assertTrue(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_negative() {
        commandAsString = "pass -2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void month_is_not_an_int() {
        commandAsString = "pass 2.2";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void month_has_unexpected_symbols() {
        commandAsString = "pass '12 ";
        assertFalse(commandValidator.validate(commandAsString));
    }

    @Test
    void month_has_letters_in_it() {
        commandAsString = "pass 12abc ";
        assertFalse(commandValidator.validate(commandAsString));
    }

}

