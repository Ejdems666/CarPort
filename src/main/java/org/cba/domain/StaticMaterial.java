package org.cba.domain;

import org.cba.domain.finder.StaticMaterialFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by adam on 15/04/2017.
 */
@Entity
public class StaticMaterial {

  public static final StaticMaterialFinder find = new StaticMaterialFinder();
    @Id
    private Integer id;

    @ManyToOne
    private Material material;

    @ManyToOne
    private Carport carport;

    private Integer amount;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
