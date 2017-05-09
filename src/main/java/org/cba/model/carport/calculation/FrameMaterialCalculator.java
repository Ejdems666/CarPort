package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 09/05/2017.
 */
public class FrameMaterialCalculator extends DuplicatingMaterialCalculator implements MaterialCalculator {
    private final Frame frame;
    private List<ListedMaterial> calculatedMaterials = new ArrayList<>();

    public FrameMaterialCalculator(Frame frame) {
        this.frame = frame;
    }

    @Override
    public List<ListedMaterial> calculateMaterialsForDimensions(int frameWidth, int frameLength) {
        addMaterialWithOptimalLengthVariation(frameLength, frame.getUpperPillarMaterial(), 2);
        addMaterialWithOptimalLengthVariation(frameWidth, frame.getUpperPillarMaterial(), 2);
        addMaterialWithOptimalLengthVariation(frameLength, frame.getLowerPillarMaterial(), 2);
        addRoofPlanks(frameWidth, frameLength);
        addVerticalPillars(frameLength);
        return calculatedMaterials;
    }

    private void addMaterialWithOptimalLengthVariation(int desiredLength, Material material, int countOfMaterials) {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(material, desiredLength);
        if (materialLength != null) {
            calculatedMaterials.add(new ListedMaterial(materialLength, countOfMaterials));
        }
    }

    private MaterialLength getOptimalMaterialLengthVariation(Material material, int desiredLength) {
        return MaterialLength.find.where()
                .material.id.eq(material.getId())
                .length.greaterOrEqualTo(desiredLength)
                .orderBy().length.desc()
                .findUnique();
    }

    private void addRoofPlanks(int frameWidth, int frameLength) {
        MaterialLength roofPlankLengthVariation = getOptimalMaterialLengthVariation(frame.getRoofPlankMaterial(), frameWidth);
        int numberOfRoofPlanks = calculateNumberOfMaterial(frame.getRoofPlankDistance(), frameLength);
        calculatedMaterials.add(new ListedMaterial(roofPlankLengthVariation, numberOfRoofPlanks));
    }

    private void addVerticalPillars(int frameLength) {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfVerticalPillars = calculateNumberOfMaterial(frame.getVerticalPillarDistance(), frameLength) * 2;
        calculatedMaterials.add(new ListedMaterial(verticalPillar, numberOfVerticalPillars));
    }
}
