package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CD extends Account {

    public CD(String id, double apr, double balance) {
        super(id, apr);
        this.balance = balance;
    }


    @Override
    public void passTimeAndCalculateAPR(int month) {
        age += month;
        for (int n = 0; n < month; n++) {
            for (int i = 0; i < 4; i++) {
                balance += ((apr / 100) / 12) * balance;
            }
        }
    }

    @Override
    public boolean canTransfer() {
        return false;
    }

    @Override
    public boolean canWithdraw(double balance, double amount, int age) {
        return (balance <= amount && age >= 12);
    }

    @Override
    public boolean canDeposit(double amount) {
        return false;
    }

    @Override
    public String getAccountStatus(Account account) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String formattedBalance = decimalFormat.format(account.getBalance());
        String formattedAPR = decimalFormat.format(account.getAPR());
        return "CD" + " " + account.getId() + " " + formattedBalance + " " + formattedAPR;
    }
}
