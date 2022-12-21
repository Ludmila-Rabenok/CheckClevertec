package model;

import java.util.Objects;

public class DiscountCard {
    private Integer number;
    private Integer percentage;

    public DiscountCard() {
    }

    public DiscountCard(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        model.DiscountCard that = (model.DiscountCard) o;
        return Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getPercentage(), that.getPercentage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getPercentage());
    }

    @Override
    public String toString() {
        return "model.DiscountCard{" +
                "number=" + number +
                ", percentage=" + percentage +
                '}';
    }

}
