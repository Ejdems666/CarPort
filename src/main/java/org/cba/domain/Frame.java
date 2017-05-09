package org.cba.domain;

import org.cba.domain.finder.FrameFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 03/05/2017.
 */
@Entity
public class Frame {

  public static final FrameFinder find = new FrameFinder();
    @Id
    private Integer id;

    @ManyToOne
    @NotNull
    private Material upperPillarMaterial;

    @ManyToOne
    @NotNull
    private Material lowerPillarMaterial;

    @ManyToOne
    @NotNull
    private Material verticalPillarMaterial;

    @ManyToOne
    @NotNull
    private Material roofPlankMaterial;

    @NotNull
    private Integer verticalPillarDistance;

    @NotNull
    private Integer roofPlankDistance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getUpperPillarMaterial() {
        return upperPillarMaterial;
    }

    public void setUpperPillarMaterial(Material upperPillarMaterial) {
        this.upperPillarMaterial = upperPillarMaterial;
    }

    public Material getLowerPillarMaterial() {
        return lowerPillarMaterial;
    }

    public void setLowerPillarMaterial(Material lowerPillarMaterial) {
        this.lowerPillarMaterial = lowerPillarMaterial;
    }

    public Material getVerticalPillarMaterial() {
        return verticalPillarMaterial;
    }

    public void setVerticalPillarMaterial(Material verticalPillarMaterial) {
        this.verticalPillarMaterial = verticalPillarMaterial;
    }

    public Material getRoofPlankMaterial() {
        return roofPlankMaterial;
    }

    public void setRoofPlankMaterial(Material roofPlankMaterial) {
        this.roofPlankMaterial = roofPlankMaterial;
    }

    public Integer getVerticalPillarDistance() {
        return verticalPillarDistance;
    }

    public void setVerticalPillarDistance(Integer verticalPillarDistance) {
        this.verticalPillarDistance = verticalPillarDistance;
    }

    public Integer getRoofPlankDistance() {
        return roofPlankDistance;
    }

    public void setRoofPlankDistance(Integer roofPlankDistance) {
        this.roofPlankDistance = roofPlankDistance;
    }
}
