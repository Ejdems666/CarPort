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
public class MaterialDependency implements PartDependency{

    public static final MaterialDependencyFinder find = new MaterialDependencyFinder();
    @Id
    private int id;

    @ManyToOne
    @NotNull
    private Material material;

    @ManyToOne
    @NotNull
    private AssemblyMaterial assemblyMaterial;

    @NotNull
    private int amountPerUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(int amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }
}
