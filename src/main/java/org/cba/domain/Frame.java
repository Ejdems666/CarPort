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
    private int id;

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

    @ManyToOne
    @NotNull
    private Material shedPlankMaterial;

    @NotNull
    private int verticalPillarFrontReserve;

    @NotNull
    private int verticalPillarBackReserve;

    @NotNull
    private int verticalPillarDistance;

    @NotNull
    private int roofPlankDistance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getVerticalPillarDistance() {
        return verticalPillarDistance;
    }

    public void setVerticalPillarDistance(int verticalPillarDistance) {
        this.verticalPillarDistance = verticalPillarDistance;
    }

    public int getRoofPlankDistance() {
        return roofPlankDistance;
    }

    public void setRoofPlankDistance(int roofPlankDistance) {
        this.roofPlankDistance = roofPlankDistance;
    }

    public int getVerticalPillarFrontReserve() {
        return verticalPillarFrontReserve;
    }

    public void setVerticalPillarFrontReserve(int verticalPillarFrontReserve) {
        this.verticalPillarFrontReserve = verticalPillarFrontReserve;
    }

    public int getVerticalPillarBackReserve() {
        return verticalPillarBackReserve;
    }

    public void setVerticalPillarBackReserve(int verticalPillarBackReserve) {
        this.verticalPillarBackReserve = verticalPillarBackReserve;
    }

    public Material getShedPlankMaterial() {
        return shedPlankMaterial;
    }

    public void setShedPlankMaterial(Material shedPlankMaterial) {
        this.shedPlankMaterial = shedPlankMaterial;
    }
}
