package machine_coding.vending_machine.state;

import machine_coding.vending_machine.Coin;
import machine_coding.vending_machine.VendingMachine;

public class HasMoneyState extends VendingMachineState {

    public HasMoneyState(VendingMachine machine) {
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
        machine.setVendingMachineState(new DispenseState(machine));
        machine.dispenseItem();
    }

    @Override
    public void refund() {
        machine.refundBalance();
        machine.reset();
        machine.setVendingMachineState(new IdleState(machine));
    }

    @Override
    public void insertCoin(Coin coin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertCoin'");
    }

}
