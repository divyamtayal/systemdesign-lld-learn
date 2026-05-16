package machine_coding.atm.dispenserchain;

import machine_coding.atm.dispenserChain.DispenseChain;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(int amount);

    boolean canDispense(int amount);
}