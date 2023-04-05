package fr.dawan.training.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;


@Entity
public class Manager extends BaseEntity{
    private String name;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @OneToOne(mappedBy = "manager")
    private Supplier supplier;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
