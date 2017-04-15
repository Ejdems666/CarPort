package org.cba.domain;

import org.cba.domain.finder.MaterialDependencyFinder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class MaterialDependency {

  public static final MaterialDependencyFinder find = new MaterialDependencyFinder();
    @Id
    private Integer id;

    @ManyToOne
    private Material baseMaterial;

    @ManyToOne
    private Material dependentMaterial;

    @NotNull
    private Integer amountPerUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getBaseMaterial() {
        return baseMaterial;
    }

    public void setBaseMaterial(Material baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    public Material getDependentMaterial() {
        return dependentMaterial;
    }

    public void setDependentMaterial(Material dependentMaterial) {
        this.dependentMaterial = dependentMaterial;
    }

    public Integer getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(Integer amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }
}
