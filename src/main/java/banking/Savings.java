package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Savings extends Account {

    private boolean maxWithdrawalMet = false;
    private int previousWithdrawMonth = -1; // Initialize to an invalid value

    public Savings(String id, double apr) {
        super(id, apr);
    }

    @Override
    public String getAccountStatus(Account account) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String formattedBalance = decimalFormat.format(account.getBalance());
        String formattedAPR = decimalFormat.format(account.getAPR());
        return "Savings" + " " + account.getId() + " " + formattedBalance + " " + formattedAPR;
    }


    @Override
    public void passTimeAndCalculateAPR(int month) {
        age += month;
        for (int n = 0; n < month; n++) {
            balance += ((apr / 100) / 12) * balance;
        }
    }


    @Override
    public boolean canTransfer() {
        return true;
    }

    @Override
    public boolean canWithdraw(double balance, double amount, int age) {
        if ((amount >= 0 && amount <= 1000) && !maxWithdrawalMet && age != previousWithdrawMonth) {
            previousWithdrawMonth = age; // Update the previous withdrawal month
            return true;
        }
        return false;
    }

    @Override
    public boolean canDeposit(double amount) {
        return (amount >= 0 && amount <= 2500);
    }

}
