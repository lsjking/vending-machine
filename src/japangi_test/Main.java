package japangi_test;

import java.util.Scanner;

public class Main {

    private static final String ADMIN_PASSWORD = "1234";

    public static void main(String[] args) {

        UserManager um = new UserManager();
        um.addUser("user1", 5);
        um.addUser("user2", 3);

        VendingMachineModel model = new VendingMachineModel();
        model.addMenu("🍜라면", 1000, 5);
        model.addMenu("🥤사이다", 1200, 3);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===========================");
            System.out.println("🍜🍜🍜🍜🍜 자 판 기 🍜🍜🍜🍜🍜");
            System.out.println("===========================");
            System.out.println("ALL KEY : 라면 구매");
            System.out.println("   #    : 관리자 모드");
            System.out.println("   0    : 종료");
            System.out.println("===========================");
            System.out.print("⌨️선택 > ");

            String input = sc.nextLine().trim();

            /* ===== 종료 ===== */
            if (input.equals("0")) {
                System.out.println("자판기를 종료합니다.");
                break;
            }

            /* ===== 관리자 모드 ===== */
            if (input.equals("#")) {
                int attempts = 3;
                boolean success = false;

                while (attempts > 0) {
                    System.out.print("관리자 비밀번호 (" + attempts + "회 남음): ");
                    String pw = sc.nextLine().trim();

                    if (pw.equals(ADMIN_PASSWORD)) {
                        success = true;
                        break;
                    } else {
                        System.out.println("비밀번호가 틀렸습니다.");
                        attempts--;
                    }
                }

                if (success) {
                    new AdminController(model, um).start();
                } else {
                    System.out.println("관리자 접근이 거부되었습니다.");
                }

                continue;
            }

            /* ===== 사용자 모드 (기본) ===== */
            new VendingMachineController(
                    model,
                    new VendingMachineView(),
                    um
            ).start();
        }

        sc.close();
    }
}