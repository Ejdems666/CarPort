package org.cba.model.carport.calculation;

import org.cba.domain.Material;
import org.cba.domain.MaterialLength;

/**
 * Created by adam on 09/05/2017.
 */
public class ListedMaterial {
    private MaterialLength materialLengthVariation;
    private int count;

    public ListedMaterial(MaterialLength materialLengthVariation, int count) {
        this.materialLengthVariation = materialLengthVariation;
        this.count = count;
    }

    public MaterialLength getMaterialLengthVariation() {
        return materialLengthVariation;
    }

    public Material getMaterial() {
        return materialLengthVariation.getMaterial();
    }

    public int getCount() {
        return count;
    }
}
