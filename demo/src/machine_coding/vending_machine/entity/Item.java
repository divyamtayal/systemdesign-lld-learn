package machine_coding.vending_machine.entity;

public class Item {
    private String code;
    private int price;
    private String name;

    public Item(String code, String name, int price) {
        this.code = code;
        this.price = price;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

}
