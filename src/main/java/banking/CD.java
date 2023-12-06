package banking;

public class CD extends Account {

    public CD(String id, double apr, double balance) {
        super(id, apr);
        this.balance = balance;
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
}
