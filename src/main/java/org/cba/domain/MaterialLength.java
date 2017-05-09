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
    private Integer id;

    @NotNull
    private Integer length;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    @ManyToOne
    @NotNull
    private Material material;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
