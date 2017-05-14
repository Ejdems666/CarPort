package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 09/05/2017.
 */
public class FrameMaterialCalculator implements MaterialCalculator {
    private final Frame frame;
    private List<ListedMaterial> calculatedMaterials = new ArrayList<>();

    public FrameMaterialCalculator(Frame frame) {
        this.frame = frame;
    }

    @Override
    public List<ListedMaterial> calculateMaterialsForDimensions(int desiredWidth, int desiredLength) throws MaterialLengthVariationNotFoundException {
        addMaterialWithOptimalLengthVariation(desiredLength, frame.getUpperPillarMaterial());
        addMaterialWithOptimalLengthVariation(desiredWidth, frame.getUpperPillarMaterial());
        addMaterialWithOptimalLengthVariation(desiredLength, frame.getLowerPillarMaterial());
        addRoofPlanksWithOptimalLength(desiredWidth, desiredLength);
        addVerticalPillars(desiredLength);
        return calculatedMaterials;
    }

    private void addMaterialWithOptimalLengthVariation(int desiredLength, Material material) throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(material, desiredLength);
        calculatedMaterials.add(new ListedMaterial(materialLength, 2));
    }

    private MaterialLength getOptimalMaterialLengthVariation(Material material, int desiredLength) throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = MaterialLength.find.where()
                .material.id.eq(material.getId())
                .length.greaterOrEqualTo(desiredLength)
                .orderBy().length.asc().setMaxRows(1)
                .findUnique();
        if (materialLength == null) {
            throw new MaterialLengthVariationNotFoundException("");
        }
        return materialLength;
    }

    private void addRoofPlanksWithOptimalLength(int frameWidth, int frameLength) throws MaterialLengthVariationNotFoundException {
        MaterialLength roofPlankLengthVariation = getOptimalMaterialLengthVariation(frame.getRoofPlankMaterial(), frameWidth);
        int numberOfRoofPlanks = (frameLength / frame.getRoofPlankDistance()) -1;
        calculatedMaterials.add(new ListedMaterial(roofPlankLengthVariation, numberOfRoofPlanks));
    }

    private void addVerticalPillars(int frameLength) {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfVPBothSides = ((frameLength / frame.getVerticalPillarDistance()) + 1) * 2;
        calculatedMaterials.add(new ListedMaterial(verticalPillar, numberOfVPBothSides));
    }
}
