package org.fasttrackit.videogameshop.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {


    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public void addProductToCart(Product product) {
        products.add(product);

        product.getCarts().add(this);
    }

    public void removeProductFromCart(Product product) {
        products.remove(products);

        product.getCarts().remove(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
