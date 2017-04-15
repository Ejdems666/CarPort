package org.cba.domain;

import org.cba.domain.finder.DynamicMaterialVariableFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class DynamicMaterialVariable {

  public static final DynamicMaterialVariableFinder find = new DynamicMaterialVariableFinder();
    @Id
    private Integer id;

    @ManyToOne
    private Material material;

    @ManyToOne
    private Carport carport;

    @NotNull
    private Integer baseLength;
    @NotNull
    private Integer baseDistance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public Integer getBaseLength() {
        return baseLength;
    }

    public void setBaseLength(Integer baseLength) {
        this.baseLength = baseLength;
    }

    public Integer getBaseDistance() {
        return baseDistance;
    }

    public void setBaseDistance(Integer baseDistance) {
        this.baseDistance = baseDistance;
    }
}
