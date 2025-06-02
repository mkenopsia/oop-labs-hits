package models;

import java.util.UUID;

public class StandardEntity {

    public StandardEntity() {}

    public StandardEntity(UUID id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public StandardEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    private UUID id;

    private String name;

    private Double price;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
