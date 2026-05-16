package machine_coding.vending_machine.state;

import machine_coding.vending_machine.Coin;
import machine_coding.vending_machine.VendingMachine;

public abstract class VendingMachineState {
    VendingMachine machine;

    public VendingMachineState(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void selectItem(String code);

    public abstract void dispenseItem();

    public abstract void refund();

    public abstract void insertCoin(Coin coin);
}
