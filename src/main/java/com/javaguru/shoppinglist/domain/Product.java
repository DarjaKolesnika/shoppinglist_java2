package com.javaguru.shoppinglist.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private String description;


    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }


    public void printInfoAboutProduct() {
        System.out.println("Actual information:");
        System.out.println("name: " + name + ", category: " + category + ", description: " + description + ", regular price: "
                + price + ", discount: " + discount + "%, current price: " +
                price.subtract(price.multiply(discount.movePointLeft(2))).setScale(2, RoundingMode.CEILING));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                "%, description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
