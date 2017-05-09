package org.cba.domain;

import org.cba.domain.finder.RoofTileFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 03/05/2017.
 */
@Entity
public class RoofTile {

  public static final RoofTileFinder find = new RoofTileFinder();

    @Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer width;

    @NotNull
    private Integer length;

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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
}
