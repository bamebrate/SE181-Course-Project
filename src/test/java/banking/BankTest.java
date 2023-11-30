package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	public static final String id = "14519762";
	public static final double apr = 8.2;
	Bank bank;

	@BeforeEach
	void setup() {
		bank = new Bank();
	}

	@Test
	void bank_has_no_accounts_initially() {
		assertTrue(bank.getAccounts().isEmpty());
	}

	@Test
	void add_account_to_bank() {
		bank.addSavingsAccount(id, apr);
		assertEquals(apr, bank.getAccounts().get(id).getAPR());
	}

	@Test
	void add_two_account_to_bank() {
		bank.addSavingsAccount(id, apr);
		bank.addSavingsAccount(id + "1", apr + 1);
		assertEquals(apr + 1, bank.getAccounts().get(id + "1").getAPR());
	}

	@Test
	void retrieve_correct_account_from_bank() {
		bank.addSavingsAccount(id, apr);
		bank.addCheckingAccount(id + "1", apr + 1);
		assertEquals(apr + 1, bank.getAccounts().get(id + "1").getAPR());
	}

	@Test
	void deposit_to_correct_account() {
		bank.addSavingsAccount(id, apr);
		bank.addCheckingAccount(id + "1", apr + 1);
		bank.depositMoneyById(id + "1", 1000);
		assertEquals(1000, bank.getAccounts().get(id + "1").getBalance());
	}

	@Test
	void withdraw_from_correct_account() {
		bank.addSavingsAccount(id, apr);
		bank.addSavingsAccount(id + "1", apr + 1);
		bank.depositMoneyById(id + "1", 1000);
		bank.withdrawMoneyById(id + "1", 200);
		assertEquals(800, bank.getAccounts().get(id + "1").getBalance());
	}

	@Test
	void deposit_twice_to_correct_account() {
		bank.addSavingsAccount(id, apr);
		bank.addCheckingAccount(id + "1", apr + 1);
		bank.depositMoneyById(id + "1", 1000);
		bank.depositMoneyById(id + "1", 200);
		assertEquals(1200, bank.getAccounts().get(id + "1").getBalance());
	}

	@Test
	void withdraw_twice_from_correct_account() {
		bank.addCheckingAccount(id, apr);
		bank.addSavingsAccount(id + "1", apr + 1);
		bank.depositMoneyById(id + "1", 1000);
		bank.withdrawMoneyById(id + "1", 200);
		bank.withdrawMoneyById(id + "1", 200);
		assertEquals(600, bank.getAccounts().get(id + "1").getBalance());
	}
}
