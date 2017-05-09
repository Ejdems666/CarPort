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
    private Integer id;
    @NotNull
    private Integer width;
    @NotNull
    private Integer height;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "material")
    @NotNull
    private List<MaterialLength> materialLengths;

    @OneToMany(mappedBy = "material")
    @NotNull
    private List<MaterialDependency> materialDependencies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
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
