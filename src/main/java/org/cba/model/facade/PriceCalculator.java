package org.cba.model.facade;

import org.cba.DynamicMaterialsCalulator;
import org.cba.domain.Carport;
import org.cba.domain.DynamicMaterial;

/**
 * Created by adam on 20/04/2017.
 */
public class PriceCalculator {

    /**
     * Recalculates the carport price when dimensions are changed
     * Compares original number of dynamic materials needed and newly calculated number of dynamic material
     */
    public int getPrice(Carport carport, Integer requestedWidth, Integer requestedLength) {
        int price = carport.getDefaultPrice();
        int originalNumberOfMaterials = 0;
        int editedNumberOfMaterials = 0;
        for (DynamicMaterial dynamicMaterial : carport.getDynamicMaterials()) {
            if (dynamicMaterial.getAffectedBy() == DynamicMaterial.WIDTH) {
                originalNumberOfMaterials = DynamicMaterialsCalulator.calculateNumberOfMaterial(
                        dynamicMaterial,
                        carport.getDefaultWidth()
                );
                editedNumberOfMaterials = DynamicMaterialsCalulator.calculateNumberOfMaterial(
                        dynamicMaterial,
                        requestedWidth
                );
            } else if (dynamicMaterial.getAffectedBy() == DynamicMaterial.LENGTH) {
                originalNumberOfMaterials = DynamicMaterialsCalulator.calculateNumberOfMaterial(
                        dynamicMaterial,
                        carport.getDefaultLength()
                );
                editedNumberOfMaterials = DynamicMaterialsCalulator.calculateNumberOfMaterial(
                        dynamicMaterial,
                        requestedLength
                );
            }
            if (originalNumberOfMaterials < editedNumberOfMaterials) {
                price += dynamicMaterial.getMaterial().getPrice() * (editedNumberOfMaterials - originalNumberOfMaterials);
            } else if (originalNumberOfMaterials > editedNumberOfMaterials) {
                price -= dynamicMaterial.getMaterial().getPrice() * (originalNumberOfMaterials - editedNumberOfMaterials);
            }
        }
        return price;
    }
}
