package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.cba.model.carport.formating.PartsFormatter;

/**
 * Created by adam on 09/05/2017.
 */
public class BareFrameMaterialCalculator implements FrameMaterialCalculator {
    private final Frame frame;
    private int desiredLength;
    private int desiredWidth;
    private MaterialLengthRecord upperSidePillars = null;
    private MaterialLengthRecord upperFBPillars = null;
    private MaterialLengthRecord lowerPillars = null;
    private MaterialLengthRecord verticalPillars = null;
    private MaterialLengthRecord roofPlanks = null;

    public BareFrameMaterialCalculator(Frame frame, int desiredWidth, int desiredLength) {
        this.frame = frame;
        this.desiredLength = desiredLength;
        this.desiredWidth = desiredWidth;
    }

    @Override
    public MaterialLengthRecord getSideUpperPillars() throws MaterialLengthVariationNotFoundException {
        if (upperSidePillars != null) {
            return upperSidePillars;
        }
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), desiredLength);
        return new MaterialLengthRecord(materialLength, 2);
    }

    private MaterialLength getOptimalMaterialLengthVariation(Material material, int desiredLength) throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = MaterialLength.find.where()
                .material.id.eq(material.getId())
                .length.greaterOrEqualTo(desiredLength)
                .orderBy().length.asc().setMaxRows(1)
                .findUnique();
        if (materialLength == null) {
            throw new MaterialLengthVariationNotFoundException(material, desiredLength);
        }
        return materialLength;
    }

    @Override
    public MaterialLengthRecord getFrontAndBackUpperPillars() throws MaterialLengthVariationNotFoundException {
        if (upperFBPillars != null) {
            return upperFBPillars;
        }
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), desiredWidth);
        return upperFBPillars = new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public MaterialLengthRecord getLowerPillars() throws MaterialLengthVariationNotFoundException {
        if (lowerPillars != null) {
            return lowerPillars;
        }
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getLowerPillarMaterial(), desiredLength);
        return lowerPillars = new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public MaterialLengthRecord getRoofPlanks() throws MaterialLengthVariationNotFoundException {
        if (roofPlanks != null) {
            return roofPlanks;
        }
        MaterialLength roofPlankLengthVariation = getOptimalMaterialLengthVariation(frame.getRoofPlankMaterial(), desiredWidth);
        int numberOfRoofPlanks = (desiredLength / frame.getRoofPlankDistance()) - 1;
        return roofPlanks = new MaterialLengthRecord(roofPlankLengthVariation, numberOfRoofPlanks);
    }

    @Override
    public MaterialLengthRecord getVerticalPillars() {
        if (verticalPillars != null) {
            return verticalPillars;
        }
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        double VPLengthArea = desiredLength - frame.getVerticalPillarBackReserve() - frame.getVerticalPillarFrontReserve();
        int numberOfFittingVPs = (int) Math.round(VPLengthArea / frame.getVerticalPillarDistance()) + 1;
        int numberOfVPsOnBothSides = numberOfFittingVPs * 2;
        return verticalPillars = new MaterialLengthRecord(verticalPillar, numberOfVPsOnBothSides);
    }

    @Override
    public void addCalculatedMaterialsToFormatter(PartsFormatter formatter) throws MaterialLengthVariationNotFoundException {
        formatter.addPartRecord(getFrontAndBackUpperPillars());
        formatter.addPartRecord(getSideUpperPillars());
        formatter.addPartRecord(getLowerPillars());
        formatter.addPartRecord(getRoofPlanks());
        formatter.addPartRecord(getVerticalPillars());
    }
}
