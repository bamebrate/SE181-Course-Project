package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsTest {

	private Savings savings;

	@BeforeEach
	void setUp() {
		savings = new Savings("14519762", 8.2);
	}

	@Test
	void savings_has_specified_apr() {
		assertEquals("14519762", savings.getId());
		assertEquals(8.2, savings.getAPR());
	}

	@Test
	void savings_has_a_balance_of_0() {
		assertEquals(0, savings.getBalance());
	}

	@Test
	void deposits_can_be_made_into_savings() {
		savings.depositMoney(300);
		assertEquals(300, savings.getBalance());
	}

	@Test
	void withdraw_from_savings() {
		savings.depositMoney(300.56);
		savings.withdrawMoney(200.56);
		assertEquals(100, savings.getBalance());
	}

	@Test
	void withdrawing_more_than_balance_from_savings() {
		savings.depositMoney(300.56);
		savings.withdrawMoney(400.56);
		assertEquals(0, savings.getBalance());
	}

	@Test
	void depositing_twice() {
		savings.depositMoney(200);
		savings.depositMoney(200);
		assertEquals(400, savings.getBalance());
	}

	@Test
	void withdrawing_twice() {
		savings.depositMoney(400);
		savings.withdrawMoney(200);
		savings.withdrawMoney(100);
		assertEquals(100, savings.getBalance());

	}
}
