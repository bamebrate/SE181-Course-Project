package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDTest {
	private CD cd;

	@BeforeEach
	void setUp() {
		cd = new CD("14519762", 8.2, 1000);
	}

	@Test
	void checking_has_specified_balance() {
		assertEquals(1000, cd.getBalance());
	}

	@Test
	void checking_has_specified_apr() {
		assertEquals("14519762", cd.getId());
		assertEquals(8.2, cd.getAPR());
	}

	@Test
	void deposits_can_be_made_into_cd() {
		cd.depositMoney(300);
		assertEquals(1300, cd.getBalance());
	}

	@Test
	void withdraw_from_cd() {
		cd.depositMoney(300.56);
		cd.withdrawMoney(200.56);
		assertEquals(1100, cd.getBalance());
	}

	@Test
	void withdrawing_more_than_balance_from_cd() {
		cd.depositMoney(300.56);
		cd.withdrawMoney(400.56);
		assertEquals(900, cd.getBalance());
	}

	@Test
	void depositing_twice() {
		cd.depositMoney(200);
		cd.depositMoney(200);
		assertEquals(1400, cd.getBalance());
	}

	@Test
	void withdrawing_twice() {
		cd.depositMoney(400);
		cd.withdrawMoney(200);
		cd.withdrawMoney(100);
		assertEquals(1100, cd.getBalance());

	}
}
