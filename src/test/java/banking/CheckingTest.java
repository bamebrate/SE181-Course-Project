package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {

	private Checking checking;

	@BeforeEach
	void setUp() {
		checking = new Checking("14519762", 8.2);
	}

	@Test
	void checking_has_specified_apr() {
		assertEquals("14519762", checking.getId());
		assertEquals(8.2, checking.getAPR());
	}

	@Test
	void checking_has_a_balance_of_0() {
		assertEquals(0, checking.getBalance());
	}

	@Test
	void deposits_can_be_made_into_checking() {
		checking.depositMoney(300);
		assertEquals(300, checking.getBalance());
	}

	@Test
	void withdraw_from_checking() {
		checking.depositMoney(300.56);
		checking.withdrawMoney(200.56);
		assertEquals(100, checking.getBalance());
	}

	@Test
	void withdrawing_more_than_balance_from_checking() {
		checking.depositMoney(300.56);
		checking.withdrawMoney(400.56);
		assertEquals(0, checking.getBalance());
	}

	@Test
	void depositing_twice() {
		checking.depositMoney(200);
		checking.depositMoney(200);
		assertEquals(400, checking.getBalance());
	}

	@Test
	void withdrawing_twice() {
		checking.depositMoney(400);
		checking.withdrawMoney(200);
		checking.withdrawMoney(100);
		assertEquals(100, checking.getBalance());

	}
}
