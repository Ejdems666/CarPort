package org.cba.domain;

import org.cba.domain.finder.MaterialLengthFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 09/05/2017.
 */
@Entity
public class MaterialLength {

  public static final MaterialLengthFinder find = new MaterialLengthFinder();

    @Id
    private int id;

    @NotNull
    private int length;

    @NotNull
    private int price;

    @NotNull
    private int stock;

    @ManyToOne
    @NotNull
    private Material material;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
