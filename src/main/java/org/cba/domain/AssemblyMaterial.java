package org.cba.domain;

import org.cba.domain.finder.AssemblyMaterialFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 03/05/2017.
 */
@Entity
public class AssemblyMaterial {

  public static final AssemblyMaterialFinder find = new AssemblyMaterialFinder();
    @Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
