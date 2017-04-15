package org.cba.domain;

import org.cba.domain.finder.MaterialFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class Material {

    public static final MaterialFinder find = new MaterialFinder();
    @Id
    private Integer id;
    @NotNull
    private Integer price;
    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
