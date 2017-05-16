package org.cba.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 16/05/2017.
 */
@Entity
public class RoofTileDependency implements PartDependency{
    @Id
    private int id;

    @ManyToOne
    @NotNull
    private RoofTile roofTile;

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

    public RoofTile getRoofTile() {
        return roofTile;
    }

    public void setRoofTile(RoofTile roofTile) {
        this.roofTile = roofTile;
    }

    @Override
    public AssemblyMaterial getAssemblyMaterial() {
        return assemblyMaterial;
    }

    public void setAssemblyMaterial(AssemblyMaterial assemblyMaterial) {
        this.assemblyMaterial = assemblyMaterial;
    }

    @Override
    public int getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(int amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }
}
