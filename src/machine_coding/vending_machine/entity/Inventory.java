package machine_coding.vending_machine.entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Item> itemMap = new HashMap<>();
    private Map<String, Integer> stockMap = new HashMap<>();

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public Map<String, Integer> getStockMap() {
        return stockMap;
    }

    public void addItem(String code, Item item, int quantity) {
        itemMap.put(code, item);
        stockMap.put(code, quantity);
    }

    public boolean isAvail(String code) {
        return stockMap.getOrDefault(code, 0) > 0;
    }

    public void reduceStock(String code) {
        stockMap.put(code, stockMap.get(code) - 1);
    }

    public Item getItem(String code) {
        return itemMap.get(code);
    }
}
