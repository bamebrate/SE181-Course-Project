package banking;

public class Checking extends Account {
    public Checking(String id, double apr) {
        super(id, apr);
    }

    @Override
    public String getType() {
        return "banking.Checking";
    }

    @Override
    public boolean canTransfer() {
        return true;
    }
}