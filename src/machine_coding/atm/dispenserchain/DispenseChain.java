package machine_coding.atm.dispenserchain;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(int amount);

    boolean canDispense(int amount);
}