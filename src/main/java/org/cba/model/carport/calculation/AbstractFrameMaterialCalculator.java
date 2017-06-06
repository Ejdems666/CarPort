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
 * Created by adam on 28/05/2017.
 */
public abstract class AbstractFrameMaterialCalculator implements FrameMaterialCalculator {
    protected final Frame frame;
    protected Dimensions frameDimensions;

    public AbstractFrameMaterialCalculator(Frame frame, Dimensions frameDimensions) {
        this.frame = frame;
        this.frameDimensions = frameDimensions;
    }

    @Override
    public PartRecord getSideUpperPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), frameDimensions.length);
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
    public PartRecord getFrontAndBackUpperPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getUpperPillarMaterial(), frameDimensions.width);
        return new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public PartRecord getLowerPillars() throws MaterialLengthVariationNotFoundException {
        MaterialLength materialLength = getOptimalMaterialLengthVariation(frame.getLowerPillarMaterial(), frameDimensions.length);
        return new MaterialLengthRecord(materialLength, 2);
    }

    @Override
    public PartRecord getRoofPlanks() throws MaterialLengthVariationNotFoundException {
        MaterialLength roofPlankLengthVariation = getOptimalMaterialLengthVariation(frame.getRoofPlankMaterial(), frameDimensions.width);
        int numberOfRoofPlanks = (frameDimensions.length / frame.getRoofPlankDistance()) - 1;
        return new MaterialLengthRecord(roofPlankLengthVariation, numberOfRoofPlanks);
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
