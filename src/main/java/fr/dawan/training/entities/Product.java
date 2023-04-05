package fr.dawan.training.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.Objects;
import java.util.Set;

@Entity
public class Product extends BaseEntity{

    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String description;
    //@JsonProperty(value = "prix") modification du nom de la propriété JSON
    @Column(name = "priceht", precision = 2)
    @Min(value = 0)
    private double price;
    @ManyToOne(cascade = CascadeType.ALL) //plusieurs produits pour 1 catégorie
    //@JoinColumn(name="cat_id")
    private Category category;
    @Transient
    private String colonneNonMappe;
    private String imagePath;

    @ManyToMany(mappedBy = "products")
    private Set<Supplier> suppliers;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
