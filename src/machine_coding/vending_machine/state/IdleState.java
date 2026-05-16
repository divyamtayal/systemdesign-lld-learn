package machine_coding.vending_machine.state;

import machine_coding.vending_machine.Coin;
import machine_coding.vending_machine.VendingMachine;
import machine_coding.vending_machine.VendingMachineException;
import machine_coding.vending_machine.entity.Item;

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
