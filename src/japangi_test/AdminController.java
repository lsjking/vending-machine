package japangi_test;

import java.util.Scanner;

class AdminController {

    private VendingMachineModel model;
    private UserManager userManager;
    private Scanner sc = new Scanner(System.in);
    private AdminView view = new AdminView();

    public AdminController(VendingMachineModel model, UserManager userManager) {
        this.model = model;
        this.userManager = userManager;
    }

    public void start() {
        while (true) {
            System.out.println("\n====== 관리자 모드 ======");
            System.out.println("[1] 메뉴 추가");
            System.out.println("[2] 재고 추가");
            System.out.println("[3] 가격 변경");
            System.out.println("[4] 매출 정산");
            System.out.println("[5] 기기 온도 설정");
            System.out.println("[6] 사용자 정보 조회");
            System.out.println("[7] 할인 ON/OFF");
            System.out.println("[0] 뒤로가기");
            System.out.print("선택: ");

            String input = sc.nextLine().trim();

            if (!input.matches("\\d+")) {
                System.out.println("[오류] 숫자만 입력하세요.");
                continue;
            }

            int sel = Integer.parseInt(input);

            if (sel == 0) {
                System.out.println("관리자 모드 종료");
                break;
            }

            switch (sel) {

                /* ================== 1. 메뉴 추가 ================== */
                case 1: {
                    while (true) {
                        System.out.print("메뉴 이름 (0: 뒤로가기): ");
                        String name = sc.nextLine().trim();

                        if (name.equals("0")) break;

                        int price;
                        while (true) {
                            System.out.print("가격 (0: 뒤로가기): ");
                            String priceInput = sc.nextLine().trim();

                            if (priceInput.equals("0")) {
                                price = -1;
                                break;
                            }

                            if (!priceInput.matches("\\d+")) {
                                System.out.println("[오류] 가격은 숫자만 입력하세요.");
                                continue;
                            }
                            price = Integer.parseInt(priceInput);
                            break;
                        }

                        if (price == -1) continue;

                        model.addMenu(name, price, 0);
                        System.out.println("메뉴 추가 완료");
                        break;
                    }
                    break;
                }

                /* ================== 2. 재고 추가 ================== */
                case 2: {
                    view.showMenu(model.menus, model.menuCount);

                    while (true) {
                        System.out.print("메뉴 번호 (0: 뒤로가기): ");
                        String idxInput = sc.nextLine().trim();

                        if (idxInput.equals("0")) break;

                        if (!idxInput.matches("\\d+")) {
                            System.out.println("[오류] 숫자만 입력하세요.");
                            continue;
                        }

                        int num = Integer.parseInt(idxInput);
                        if (num < 1 || num > model.menuCount) {
                            System.out.println("[오류] 유효한 메뉴 번호를 입력하세요.");
                            continue;
                        }

                        int qty;
                        while (true) {
                            System.out.print("추가 수량 (0: 뒤로가기): ");
                            String qtyInput = sc.nextLine().trim();

                            if (qtyInput.equals("0")) {
                                qty = -1;
                                break;
                            }

                            if (!qtyInput.matches("\\d+")) {
                                System.out.println("[오류] 숫자만 입력하세요.");
                                continue;
                            }
                            qty = Integer.parseInt(qtyInput);
                            break;
                        }

                        if (qty == -1) continue;

                        model.menus[num - 1].stock += qty;
                        System.out.println("재고 추가 완료");
                        break;
                    }
                    break;
                }

                /* ================== 3. 가격 변경 ================== */
                case 3: {
                    view.showMenu(model.menus, model.menuCount);

                    while (true) {
                        System.out.print("메뉴 번호 (0: 뒤로가기): ");
                        String idxInput = sc.nextLine().trim();

                        if (idxInput.equals("0")) break;

                        if (!idxInput.matches("\\d+")) {
                            System.out.println("[오류] 숫자만 입력하세요.");
                            continue;
                        }

                        int num = Integer.parseInt(idxInput);
                        if (num < 1 || num > model.menuCount) {
                            System.out.println("[오류] 유효한 메뉴 번호를 입력하세요.");
                            continue;
                        }

                        int newPrice;
                        while (true) {
                            System.out.print("새 가격 (0: 뒤로가기): ");
                            String priceInput = sc.nextLine().trim();

                            if (priceInput.equals("0")) {
                                newPrice = -1;
                                break;
                            }

                            if (!priceInput.matches("\\d+")) {
                                System.out.println("[오류] 숫자만 입력하세요.");
                                continue;
                            }
                            newPrice = Integer.parseInt(priceInput);
                            break;
                        }

                        if (newPrice == -1) continue;

                        model.menus[num - 1].price = newPrice;
                        System.out.println("가격 변경 완료");
                        break;
                    }
                    break;
                }

                /* ================== 4. 매출 정산 ================== */
                case 4: {
                    int sum = 0;
                    for (int i = 0; i < model.menuCount; i++) {
                        sum += model.menus[i].revenue;
                    }
                    System.out.println("총 매출: " + sum + "원");
                    break;
                }

                /* ================== 5. 기기 온도 ================== */
                case 5: {
                    while (true) {
                        System.out.print("설정 온도 (0: 뒤로가기): ");
                        String tempInput = sc.nextLine().trim();

                        if (tempInput.equals("0")) break;

                        if (!tempInput.matches("\\d+")) {
                            System.out.println("[오류] 숫자만 입력하세요.");
                            continue;
                        }

                        model.machineTemp = Integer.parseInt(tempInput);
                        System.out.println("온도 설정 완료");
                        break;
                    }
                    break;
                }

                /* ================== 6. 사용자 조회 ================== */
                case 6: {
                    System.out.print("사용자 ID (0: 뒤로가기): ");
                    String uid = sc.nextLine().trim();

                    if (uid.equals("0")) break;

                    User u = userManager.findUser(uid);
                    if (u != null) {
                        System.out.println("스탬프: " + u.stamps);
                        System.out.println("쿠폰: " + u.coupons);
                    } else {
                        System.out.println("사용자 없음");
                    }
                    break;
                }

                /* ================== 7. 할인 ON/OFF ================== */
                case 7: {
                    view.showMenu(model.menus, model.menuCount);

                    while (true) {
                        System.out.print("메뉴 번호 (0: 뒤로가기): ");
                        String idxInput = sc.nextLine().trim();

                        if (idxInput.equals("0")) break;

                        if (!idxInput.matches("\\d+")) {
                            System.out.println("[오류] 숫자만 입력하세요.");
                            continue;
                        }

                        int num = Integer.parseInt(idxInput);
                        if (num < 1 || num > model.menuCount) {
                            System.out.println("[오류] 유효한 메뉴 번호를 입력하세요.");
                            continue;
                        }

                        Menu m = model.menus[num - 1];
                        m.discount = !m.discount;

                        System.out.println("할인 상태: " + (m.discount ? "ON" : "OFF"));
                        break;
                    }
                    break;
                }

                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}