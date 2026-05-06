package japangi_test;

class AdminView {
    void showMenu(Menu[] menus, int count) {
        System.out.println("=== 관리자 메뉴 목록 ===");
        for (int i = 0; i < count; i++) {
            Menu m = menus[i];
            System.out.println(
                (i + 1) + ". " + m.name +
                " / " + m.price + "원 / 재고 " + m.stock +
                (m.discount ? " / [할인중]" : "")
            );
        }
    }
}