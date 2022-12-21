package util;

public class IdGenerator {
    private static Integer productId = 1;
    private static Integer discountCardNumber = 1000;
    private static Integer checkId = 1;

    public static Integer generateProductId() {
        return productId++;
    }

    public static Integer generateDiscountCardNumber() {
        return discountCardNumber++;
    }

    public static Integer generateCheckId(){
        return checkId++;
    }

}
