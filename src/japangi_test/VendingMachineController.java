package japangi_test;

import java.util.Scanner;

class VendingMachineController {

    VendingMachineModel model;
    VendingMachineView view;
    UserManager userManager;
    Scanner sc = new Scanner(System.in);

    private final int PURCHASE_LIMIT = 8;

    VendingMachineController(VendingMachineModel m,
                             VendingMachineView v,
                             UserManager um) {
        model = m;
        view = v;
        userManager = um;
    }

    void start() {

        model.useCoupon = false;
        model.currentUser = null;
        model.money = 0;

        int purchaseCount = 0;

        System.out.print("🔎아이디 (없으면 guest): ");
        String id = sc.nextLine().trim();
        model.currentUser = userManager.findUser(id);

        if (model.currentUser == null) {
            view.showMessage("👤비회원으로 진행합니다.");
        } else if (model.currentUser.coupons > 0) {
            System.out.print("📜쿠폰 사용? (y/n): ");
            model.useCoupon = sc.nextLine().equalsIgnoreCase("y");
        }

        while (true) {
            System.out.print("💵투입 금액: ");
            String moneyInput = sc.nextLine().trim();

            if (!moneyInput.matches("\\d+")) {
                view.showMessage("숫자만 입력하세요.");
                continue;
            }

            model.money = Integer.parseInt(moneyInput);
            break;
        }

        while (purchaseCount < PURCHASE_LIMIT) {

            view.showMenus(model.menus, model.menuCount);
            System.out.print("⌨️선택 (0 종료): ");

            String input = sc.nextLine().trim();

            if (!input.matches("\\d+")) {
            	System.out.println("[오류] 숫자만 입력하세요. 다시 선택");
                continue;
            }

            int sel = Integer.parseInt(input);

            if (sel == 0) break;

            Menu m = model.getMenu(sel - 1);
            if (m == null) {
                view.showMessage("잘못된 메뉴 번호");
                continue;
            }

            if (model.purchase(m)) {
                view.showMessage("🛒구매 완료: " + m.name);
                purchaseCount++;
            } else {
                view.showMessage("⛔구매 실패 (잔액/재고 확인)");
            }
        }

        view.showMessage("💰잔돈 반환: " + model.refund());
    }
}
