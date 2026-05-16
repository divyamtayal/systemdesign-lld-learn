package vendingMachine.state;

import vendingMachine.Coin;
import vendingMachine.VendingMachine;

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
