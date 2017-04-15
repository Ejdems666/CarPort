package org.cba.domain;

import javax.persistence.*;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class DefaultMaterial {
    @Id
    private Integer id;

    @ManyToOne
    private Material material;

    @ManyToOne
    private Carport carport;

    private Integer amount;

    private Integer dynamic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDynamic() {
        return dynamic;
    }

    public void setDynamic(Integer dynamic) {
        this.dynamic = dynamic;
    }
}
