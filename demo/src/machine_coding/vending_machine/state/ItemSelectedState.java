package machine_coding.vending_machine.state;

import machine_coding.vending_machine.Coin;
import machine_coding.vending_machine.VendingMachine;
import machine_coding.vending_machine.VendingMachineException;

public class ItemSelectedState extends VendingMachineState {

    public ItemSelectedState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        // TODO Auto-generated method stub
        throw new VendingMachineException("Item Already selected");
    }

    @Override
    public void dispenseItem() {
        // TODO Auto-generated method stub
        throw new VendingMachineException("please insert coins");
    }

    @Override
    public void refund() {
        // TODO Auto-generated method stub
        throw new VendingMachineException("nothing to refund");
    }

    @Override
    public void insertCoin(Coin coin) {
        machine.setBalance(coin.getCoin());
        System.out.println("Coin Insertedd: " + coin.getCoin());

        int price = machine.getSelectedItem1().getPrice();

        if (machine.getBalance() >= price) {
            System.out.println("Suffient money recieved");
            machine.setVendingMachineState(new HasMoneyState(machine));
        }
    }

}
