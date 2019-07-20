package com.javaguru.shoppinglist.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", columnDefinition = "ENUM")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Cart> carts = new HashSet<>();

    public Product() {

    }

    public Product(String name, BigDecimal price, BigDecimal discount, String description, Long id, Long cartId, Category category) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.id = id;
        this.cartId = cartId;
        this.category = category;
    }

    public Product(Product other) {
        this(other.getName(), other.getPrice(), other.getDiscount(), other.getDescription(), other.getId(),
                other.getCartId(), other.getCategory());
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
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

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String printInfoAboutProduct() {
        return "Actual information(" + "id=" + id + ", cartId=" + cartId +
                ", name: " + name + ", category: " + category + ", description: " + description + ", regular price: "
                + price + ", discount: " + discount + "%, current price: " +
                price.subtract(price.multiply(discount.movePointLeft(2))).setScale(2, RoundingMode.CEILING) + ")";
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
        return Objects.hash(id, name, description, cartId);
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
