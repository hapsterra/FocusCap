package com.example.focuscap;

import java.util.Date;

public class Sale {
    private int id;
    private Product product;
    private User user;
    private Date date;
    private float price;

    public Sale(int id, Product product, User user, Date date, float price) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
