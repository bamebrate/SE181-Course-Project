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


}
