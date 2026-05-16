package vendingMachine.state;

import vendingMachine.Coin;
import vendingMachine.VendingMachine;
import vendingMachine.VendingMachineException;
import vendingMachine.entity.Item;

public class IdleState extends VendingMachineState {

    public IdleState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        if (!machine.getInventory().isAvail(code)) {
            System.out.println("Item not avail");
        }
        machine.setSelectedItem(code);
        machine.setVendingMachineState(new ItemSelectedState(machine));
        System.out.println("Item Selected: " + code);
    }

    @Override
    public void dispenseItem() {
        // TODO Auto-generated method stub
        throw new VendingMachineException("Please select Item first");
    }

    @Override
    public void refund() {
        // TODO Auto-generated method stub
        throw new VendingMachineException("nothing to refund");

    }

    @Override
    public void insertCoin(Coin coin) {
        // TODO Auto-generated method stub
        throw new VendingMachineException("Please select Item first");
    }

}
