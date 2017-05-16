package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.cba.model.carport.formating.PartRecordsFormatter;

/**
 * Created by adam on 09/05/2017.
 */
public class BareFrameMaterialCalculator implements FrameMaterialCalculator {
    private final Frame frame;
    private int desiredLength;
    private int desiredWidth;

    public BareFrameMaterialCalculator(Frame frame, int desiredWidth, int desiredLength) {
        this.frame = frame;
        this.desiredLength = desiredLength;
        this.desiredWidth = desiredWidth;
    }

    @Override
    public MaterialLengthRecord getSideUpperPillars() throws MaterialLengthVariationNotFoundException {
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
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), desiredWidth);
        return new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public MaterialLengthRecord getLowerPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getLowerPillarMaterial(), desiredLength);
        return new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public MaterialLengthRecord getRoofPlanksPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength roofPlankLengthVariation = getOptimalMaterialLengthVariation(frame.getRoofPlankMaterial(), desiredWidth);
        int numberOfRoofPlanks = (desiredLength / frame.getRoofPlankDistance()) - 1;
        return new MaterialLengthRecord(roofPlankLengthVariation, numberOfRoofPlanks);
    }

    @Override
    public MaterialLengthRecord getVerticalPillars() {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfVPBothSides = ((desiredLength / frame.getVerticalPillarDistance()) + 1) * 2;
        return new MaterialLengthRecord(verticalPillar, numberOfVPBothSides);
    }

    @Override
    public void addCalculatedMaterialsToFormatter(PartRecordsFormatter formatter) throws MaterialLengthVariationNotFoundException {
        formatter.addPartRecord(getFrontAndBackUpperPillars());
        formatter.addPartRecord(getSideUpperPillars());
        formatter.addPartRecord(getLowerPillars());
        formatter.addPartRecord(getRoofPlanksPillars());
        formatter.addPartRecord(getVerticalPillars());
    }
}