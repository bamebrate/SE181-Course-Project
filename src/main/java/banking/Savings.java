package banking;

public class Savings extends Account {

    public Savings(String id, double apr) {
        super(id, apr);
    }


    @Override
    public boolean canTransfer() {
        return true;
    }

    @Override
    public boolean canWithdraw(double balance, double amount, int age) {
        return (amount >= 0 && amount <= 1000);
    }

    @Override
    public boolean canDeposit(double amount) {
        return (amount >= 0 && amount <= 2500);
    }

}
