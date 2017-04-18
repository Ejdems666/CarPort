package org.cba;

import org.cba.domain.DynamicMaterial;

/**
 * Created by adam on 18/04/2017.
 */
public class DynamicMaterialsCalulator {
    public static int calculateNumberOfMaterial(DynamicMaterial dynamicMaterial, int selectedSize) {
        return (selectedSize/dynamicMaterial.getBaseDistance())+1;
    }

    public static int calculateDistanceBetweenMaterials(int numberOfMaterials, int selectedSize) {
        return selectedSize/(numberOfMaterials-1);
    }
}
