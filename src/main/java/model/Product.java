package model;

public class Product {
    private long id;
    private double price;
    private String name;
    private String description;

    public Product() {

    }

    public Product(String name, double price, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Product(long id, String name, double price, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
