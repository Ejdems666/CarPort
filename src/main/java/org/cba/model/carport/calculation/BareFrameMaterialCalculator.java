package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.cba.model.carport.formating.PartRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 09/05/2017.
 */
public class BareFrameMaterialCalculator implements FrameMaterialCalculator {
    private final Frame frame;
    private Dimensions carportDimensions;

    public BareFrameMaterialCalculator(Frame frame, Dimensions carportDimensions) {
        this.frame = frame;
        this.carportDimensions = carportDimensions;
    }

    @Override
    public MaterialLengthRecord getSideUpperPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), carportDimensions.length);
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
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), carportDimensions.width);
        return new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public MaterialLengthRecord getLowerPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getLowerPillarMaterial(), carportDimensions.length);
        return new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public MaterialLengthRecord getRoofPlanks() throws MaterialLengthVariationNotFoundException {
        MaterialLength roofPlankLengthVariation = getOptimalMaterialLengthVariation(frame.getRoofPlankMaterial(), carportDimensions.width);
        int numberOfRoofPlanks = (carportDimensions.length / frame.getRoofPlankDistance()) - 1;
        return new MaterialLengthRecord(roofPlankLengthVariation, numberOfRoofPlanks);
    }

    @Override
    public MaterialLengthRecord getVerticalPillars() {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfVPBothSides = ((carportDimensions.length / frame.getVerticalPillarDistance()) + 1) * 2;
        return new MaterialLengthRecord(verticalPillar, numberOfVPBothSides);
    }

    @Override
    public List<PartRecord> getAllPartRecords() throws MaterialLengthVariationNotFoundException {
        List<PartRecord> partRecords = new ArrayList<>();
        partRecords.add(getFrontAndBackUpperPillars());
        partRecords.add(getSideUpperPillars());
        partRecords.add(getLowerPillars());
        partRecords.add(getRoofPlanks());
        partRecords.add(getVerticalPillars());
        return partRecords;
    }
}
