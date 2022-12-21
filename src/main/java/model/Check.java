package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public class Check {
    private Integer id;
    private LocalDateTime date;
    private HashMap<Integer, Integer> productIdAndAmount = new HashMap<>();
    private Integer discountCardNumber;

    public Check() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public HashMap<Integer, Integer> getProductIdAndAmount() {
        return productIdAndAmount;
    }

    public void setProductIdAndAmount(HashMap<Integer, Integer> productIdAndAmount) {
        this.productIdAndAmount = productIdAndAmount;
    }

    public void addProductInHashMap(Integer productId, Integer amount) {
        productIdAndAmount.put(productId, amount);
    }

    public Integer getDiscountCardNumber() {
        return discountCardNumber;
    }

    public void setDiscountCardNumber(Integer discountCardNumber) {
        this.discountCardNumber = discountCardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        model.Check check = (model.Check) o;
        return Objects.equals(getId(), check.getId()) &&
                Objects.equals(getDate(), check.getDate()) &&
                Objects.equals(getProductIdAndAmount(), check.getProductIdAndAmount()) &&
                Objects.equals(getDiscountCardNumber(), check.getDiscountCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getProductIdAndAmount(), getDiscountCardNumber());
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", date=" + date +
                ", productIdAndAmount=" + productIdAndAmount +
                ", discountCardNumber=" + discountCardNumber +
                '}';
    }

}
