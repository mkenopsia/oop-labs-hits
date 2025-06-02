package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;

    private UUID userId;

    private LocalDateTime date = LocalDateTime.now();

    private String status;

    private String comment;

    private Double price;

    private List<Pizza> pizzasForOrder = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Pizza> getPizzasForOrder() {
        return pizzasForOrder;
    }

    public void setPizzasForOrder(List<Pizza> pizzasForOrder) {
        this.pizzasForOrder = pizzasForOrder;
    }
}
