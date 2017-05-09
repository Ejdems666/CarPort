package org.cba.model.carport.calculation;

/**
 * Created by adam on 18/04/2017.
 */
public abstract class DuplicatingMaterialCalculator {
    protected int calculateNumberOfMaterial(int baseDistance, int desiredSize) {
        return (desiredSize/baseDistance)+1;
    }

    protected int calculateDistanceBetweenMaterials(int numberOfMaterials, int desiredSize) {
        return desiredSize/(numberOfMaterials-1);
    }
}
