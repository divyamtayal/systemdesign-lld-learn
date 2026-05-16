package vendingMachine;

import java.util.Map;

import vendingMachine.entity.Inventory;
import vendingMachine.entity.Item;
import vendingMachine.state.IdleState;
import vendingMachine.state.VendingMachineState;

public class VendingMachine {
    private static VendingMachine INSTANCE;
    private Inventory inventory = new Inventory();
    private VendingMachineState vendingMachineState;
    private String selectedItem;
    private int balance = 0;

    private VendingMachine() {
        vendingMachineState = new IdleState(this);
    }

    public static VendingMachine getInstance() {
        if (INSTANCE == null) {
            synchronized (VendingMachine.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VendingMachine();
                }
            }
        }

        return INSTANCE;
    }

    public void insertCoin(Coin coin) {
        vendingMachineState.insertCoin(coin);
    }

    public void selectItem(String code) {
        vendingMachineState.selectItem(code);
    }

    public void dispense() {
        vendingMachineState.dispenseItem();
    }

    public void refundBalance() {
        System.out.println("Refunding balance");
        balance = 0;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setBalance(int balance) {
        this.balance += balance;
    }

    public VendingMachineState getVendingMachineState() {
        return vendingMachineState;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public int getBalance() {
        return balance;
    }

    public Item getSelectedItem1() {
        return inventory.getItem(selectedItem);
    }

    public void reset() {
        selectedItem = null;
        balance = 0;
    }

    public void dispenseItem() {
        Item item = inventory.getItem(selectedItem);
        if (balance >= item.getPrice()) {
            inventory.reduceStock(selectedItem);
            balance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());
            if (balance > 0) {
                System.out.println("Returning change: " + balance);
            }
        }
        reset();
        setState(new IdleState(this));
    }

    public void setState(VendingMachineState state) {
        this.vendingMachineState = state;
    }

    public void addItem(String code, String name, int price, int quantity) {
        // TODO Auto-generated method stub
        Item item = new Item(code, name, price);
        inventory.addItem(code, item, quantity);
    }

}
