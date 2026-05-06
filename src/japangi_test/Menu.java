package japangi_test;

class Menu {
    String name;
    int price;
    int stock;
    int soldCount;
    int revenue;
    boolean discount;

    Menu(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.soldCount = 0;
        this.revenue = 0;
        this.discount = false;
    }

    boolean isAvailable() {
        return stock > 0;
    }
}
