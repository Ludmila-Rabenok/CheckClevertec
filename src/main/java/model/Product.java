package model;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Boolean auction;

    public Product(String name, Double price, Boolean auction) {
        this.name = name;
        this.price = price;
        this.auction = auction;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAuction() {
        return auction;
    }

    public void setAuction(Boolean auction) {
        this.auction = auction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        model.Product product = (model.Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getAuction(), product.getAuction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getAuction());
    }

    @Override
    public String toString() {
        return "model.Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", auction=" + auction +
                '}';
    }

}
