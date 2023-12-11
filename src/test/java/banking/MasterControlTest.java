package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    private MasterControl masterControl;
    private List<String> input;

    @BeforeEach
    void setup() {
        input = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank),
                new CommandStorage(bank));
    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add("deposittt 12345678 100");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("deposittt 12345678 100", actual);

    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    void unexpected_extra_spaces_in_beginning_is_invalid() {
        input.add(" create savings 12345678 0.01");
        assertSingleCommand(" create savings 12345678 0.01", masterControl.start(input));
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 1.0");
        input.add("deposittt 12345678 100");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", actual.get(0));
        assertEquals("deposittt 12345678 100", actual.get(1));

    }

    @Test
    void invalid_to_create_accounts_with_same_ID() {
        input.add("create checking 12345678 1.0");
        input.add("create checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Checking 12345678 0.00 1.00", actual.get(0));
        assertEquals("create checking 12345678 1.0", actual.get(1));

    }

    @Test
    void sample_list_of_inputs2() {
        input.add("Create savings 12345678 0.6");
        input.add("DePosit 12345678 90");
        input.add("Pass 3");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Savings 12345678 15.06 0.60", actual.get(0));
        assertEquals("DePosit 12345678 90", actual.get(1));

    }

    @Test
    void sample_list_of_inputs() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Withdraw 12345678 200");
        input.add("Pass 1");
        input.add("Withdraw 12345678 200");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Create cd 23456789 1.2 2000");

        List<String> actual = masterControl.start(input);
        Set<String> expectedSet = new HashSet<>(Arrays.asList(
                "Savings 12345678 600.25 0.60",
                "Deposit 12345678 700",
                "Withdraw 12345678 200",
                "Withdraw 12345678 200",
                "Transfer 98765432 12345678 300",
                "Checking 98765432 0.00 0.01",
                "Deposit 98765432 300",
                "Transfer 98765432 12345678 300",
                "Cd 23456789 2000.00 1.20"
        ));
        Set<String> actualSet = new HashSet<>(actual);

        assertEquals(expectedSet, actualSet);

    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }


}
