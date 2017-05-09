package org.cba.domain;

import org.cba.domain.finder.MaterialDependencyFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @NotNull
    private Material material;

    @ManyToOne
    @NotNull
    private AssemblyMaterial assemblyMaterial;

    @NotNull
    private Integer amountPerUnit;

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

    public AssemblyMaterial getAssemblyMaterial() {
        return assemblyMaterial;
    }

    public void setAssemblyMaterial(AssemblyMaterial assemblyMaterial) {
        this.assemblyMaterial = assemblyMaterial;
    }

    public Integer getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(Integer amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }
}
