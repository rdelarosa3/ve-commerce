package com.salon.visibleelegance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name cant be empty")
    @Size(min = 2,message = "That name is too short")
    @Column(nullable = false, length = 250)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 250)
    private String image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;

    // relationships

    // one product to many appointment items
    @OneToMany(mappedBy = "product")
    private Set<AppointmentItem> appointmentItems = new HashSet<>();

    // one product to many order items
    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems = new HashSet<>();

    // one product item to many invetory order items
    @OneToMany(mappedBy = "product")
    private Set<InventoryOrderItem> inventoryOrderItems = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="product_categories",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="product_promotions",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="promotion_id")
    )
    private Set<Promotion> promotions = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Measurement> measurements = new HashSet<>();

    @ManyToOne
    private Brand brand;

    public Product(){}

    // setter
    public Product(String name, String description){
        this.name = name;
        this.description = description;
    }

    // getter
    public Product(long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<InventoryOrderItem> getInventoryOrderItems() {
        return inventoryOrderItems;
    }

    public void setInventoryOrderItems(Set<InventoryOrderItem> inventoryOrderItems) {
        this.inventoryOrderItems = inventoryOrderItems;
    }

    public Set<AppointmentItem> getAppointmentItems() {
        return appointmentItems;
    }

    public void setAppointmentItems(Set<AppointmentItem> appointmentItems) {
        this.appointmentItems = appointmentItems;
    }
}
