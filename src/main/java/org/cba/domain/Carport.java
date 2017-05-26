package org.cba.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.cba.domain.finder.CarportFinder;
import org.cba.model.carport.calculation.Dimensions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class Carport {

    public static final CarportFinder find = new CarportFinder();
    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int defaultPrice;
    @NotNull
    private int profitFromMaterials;
    @NotNull
    private int defaultWidth;
    @NotNull
    private int defaultLength;

    @JsonManagedReference
    private String description;

    @OneToMany(mappedBy = "carport")
    private List<Picture> pictures;

    @ManyToOne
    @NotNull
    private Picture thumbnail;

    @ManyToOne
    @NotNull
    private Frame frame;

    @ManyToOne
    @NotNull
    private RoofTile roofTile;

    public Picture getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Picture thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Picture> getPictures() {
        return pictures;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public int getDefaultLength() {
        return defaultLength;
    }

    public void setDefaultLength(int defaultLength) {
        this.defaultLength = defaultLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getProfitFromMaterials() {
        return profitFromMaterials;
    }

    public void setProfitFromMaterials(int profitFromMaterials) {
        this.profitFromMaterials = profitFromMaterials;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public RoofTile getRoofTile() {
        return roofTile;
    }

    public void setRoofTile(RoofTile roofTile) {
        this.roofTile = roofTile;
    }

    public Dimensions getDefaultDimensions() {
        return new Dimensions(defaultLength,defaultWidth);
    }
}

