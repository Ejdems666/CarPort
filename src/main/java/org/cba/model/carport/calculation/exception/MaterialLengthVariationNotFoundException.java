package org.cba.model.carport.calculation.exception;

import org.cba.domain.Material;

/**
 * Created by adam on 14/05/2017.
 */
public class MaterialLengthVariationNotFoundException extends Exception {
    public MaterialLengthVariationNotFoundException(Material material, int desiredLength) {
        super("Material " + material.getName() + " length variation of [" + desiredLength + " cm] wasn't found.");
    }
}
