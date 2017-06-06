package org.cba.domain;

import org.cba.domain.finder.RoofTileFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by adam on 03/05/2017.
 */
@Entity
public class RoofTile{

  public static final RoofTileFinder find = new RoofTileFinder();

    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int width;

    @NotNull
    private int widthOverlap;

    @NotNull
    private int lengthOverlap;

    @NotNull
    private int length;

    @NotNull
    private String description;

    @NotNull
    private int price;

    @NotNull
    private int stock;

    @OneToMany(mappedBy = "roofTile")
    @NotNull
    private List<RoofTileDependency> roofTileDependencies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public int getWidthOverlap() {
        return widthOverlap;
    }

    public void setWidthOverlap(int widthOverlap) {
        this.widthOverlap = widthOverlap;
    }

    public int getLengthOverlap() {
        return lengthOverlap;
    }

    public void setLengthOverlap(int lengthOverlap) {
        this.lengthOverlap = lengthOverlap;
    }

    public List<RoofTileDependency> getRoofTileDependencies() {
        return roofTileDependencies;
    }
}
