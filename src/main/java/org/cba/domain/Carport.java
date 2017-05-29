package org.cba.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.cba.domain.finder.CarportFinder;
import org.cba.model.carport.calculation.CarportSettings;
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
public class Carport implements CarportSettings {

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
    private int frameWidth;
    @NotNull
    private int frameLength;

    private boolean withShed;
    @NotNull
    private int shedWidth;
    @NotNull
    private int shedLength;

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

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameLength() {
        return frameLength;
    }

    public void setFrameLength(int frameLength) {
        this.frameLength = frameLength;
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

    @Override
    public Dimensions getFrameDimensions() {
        return new Dimensions(frameLength, frameWidth);
    }

    @Override
    public Dimensions getShedDimensions() {
        return new Dimensions(shedLength, shedWidth);
    }

    @Override
    public boolean isWithShed() {
        return withShed;
    }

    public void setWithShed(boolean withShed) {
        this.withShed = withShed;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }
}

