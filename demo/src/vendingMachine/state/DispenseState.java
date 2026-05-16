package vendingMachine.state;

import vendingMachine.Coin;
import vendingMachine.VendingMachine;

public class DispenseState extends VendingMachineState {

    public DispenseState(VendingMachine machine) {
        super(machine);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void selectItem(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectItem'");
    }

    @Override
    public void dispenseItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispenseItem'");
    }

    @Override
    public void refund() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refund'");
    }

    @Override
    public void insertCoin(Coin coin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertCoin'");
    }

}
