package japangi_test;

class VendingMachineView {

    void showMenus(Menu[] menus, int count) {
        System.out.println("=== 메뉴 ===");
        for (int i = 0; i < count; i++) {
            Menu m = menus[i];
            System.out.println(
                    (i + 1) + ". " + m.name +
                    " (" + m.price + "원 / 재고 " + m.stock +
                    (m.discount ? " / 📢할인중" : "") + ")"
            );
        }
    }

    void showMessage(String msg) {
        System.out.println(msg);
    }
}
