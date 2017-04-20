package org.cba.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.cba.domain.finder.CarportFinder;

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
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer defaultPrice;
    @NotNull
    private Integer defaultWidth;
    @NotNull
    private Integer defaultLength;

    @JsonManagedReference
    private String description;

    @OneToMany(mappedBy = "carport")
    private List<StaticMaterial> staticMaterials;

    @OneToMany(mappedBy = "carport")
    private List<DynamicMaterial> dynamicMaterials;

    @OneToMany(mappedBy = "carport")
    private List<Picture> pictures;

    @ManyToOne
    private Picture picture;

    public List<DynamicMaterial> getDynamicMaterials() {
        return dynamicMaterials;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Integer defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Integer getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(Integer defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public Integer getDefaultLength() {
        return defaultLength;
    }

    public void setDefaultLength(Integer defaultLength) {
        this.defaultLength = defaultLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaticMaterial> getStaticMaterials() {
        return staticMaterials;
    }
}

