package pizzashop.model;

public class MenuItem {
    private String menuItem;
    private Integer quantity;
    private Double price;

    public MenuItem(String mItem, Integer mQuantity, Double mPrice) {
        this.menuItem = mItem;
        this.quantity = mQuantity;
        this.price = mPrice;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}