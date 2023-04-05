package fr.dawan.training.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class Supplier extends BaseEntity{
    private String name;

    @ManyToMany
    private Set<Product> products;

    @OneToOne
    private Manager manager;
}
