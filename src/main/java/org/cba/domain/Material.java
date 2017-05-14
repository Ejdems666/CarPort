package org.cba.domain;

import org.cba.domain.finder.MaterialFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class Material {

    public static final MaterialFinder find = new MaterialFinder();
    @Id
    private int id;
    @NotNull
    private int width;
    @NotNull
    private int height;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "material")
    @NotNull
    private List<MaterialLength> materialLengths;

    @OneToMany(mappedBy = "material")
    @NotNull
    private List<MaterialDependency> materialDependencies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MaterialLength> getMaterialLengths() {
        return materialLengths;
    }

    public void setMaterialLengths(List<MaterialLength> materialLengths) {
        this.materialLengths = materialLengths;
    }

    public List<MaterialDependency> getMaterialDependencies() {
        return materialDependencies;
    }

    public void setMaterialDependencies(List<MaterialDependency> materialDependencies) {
        this.materialDependencies = materialDependencies;
    }
}
