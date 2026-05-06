package japangi_test;

class VendingMachineModel {

    Menu[] menus = new Menu[10];
    int menuCount = 0;
    int money = 0;

    User currentUser;
    boolean useCoupon = false;
    int machineTemp = 5;

    void addMenu(String name, int price, int stock) {
        menus[menuCount++] = new Menu(name, price, stock);
    }

    Menu getMenu(int index) {
        if (index < 0 || index >= menuCount) return null;
        return menus[index];
    }

    int calculatePrice(Menu m) {
        int price = m.price;

        if (m.discount) {
            price *= 0.9;
        } else if (useCoupon && currentUser != null && currentUser.useCoupon()) {
            useCoupon = false;
            price *= 0.9;
        }
        return price;
    }

    boolean purchase(Menu m) {
        if (m == null || !m.isAvailable()) return false;

        int price = calculatePrice(m);
        if (money < price) return false;

        money -= price;
        m.stock--;
        m.soldCount++;
        m.revenue += price;

        if (currentUser != null) {
            currentUser.addStamp();
        }
        return true;
    }

    int refund() {
        int change = money;
        money = 0;
        return change;
    }
}