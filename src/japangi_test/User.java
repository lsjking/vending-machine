package japangi_test;

class User {
    String id;
    int stamps;
    int coupons;

    User(String id, int stamps) {
        this.id = id;
        this.stamps = stamps;
        this.coupons = 0;
    }

    void addStamp() {
        stamps++;

        if (stamps < 5) {
            System.out.println("✅스탬프 1개 적립 (현재: " + stamps + "개)");
        } else {
            stamps -= 5;
            coupons++;
            System.out.println("✅✅✅✅✅스탬프 5개 → 📜쿠폰 1장 발급");
        }
    }


    boolean useCoupon() {
        if (coupons > 0) {
            coupons--;
            return true;
        }
        return false;
    }
}