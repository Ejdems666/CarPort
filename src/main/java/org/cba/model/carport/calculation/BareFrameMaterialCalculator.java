package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.cba.model.carport.formating.PartRecord;

/**
 * Created by adam on 09/05/2017.
 */
public class BareFrameMaterialCalculator extends AbstractFrameMaterialCalculator {
    public BareFrameMaterialCalculator(Frame frame, Dimensions dimensions) {
        super(frame,dimensions);
    }

    @Override
    public PartRecord getVerticalPillars() {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        float realVPArea = frameDimensions.length - frame.getVerticalPillarFrontReserve() - frame.getVerticalPillarBackReserve();
        int VPAtTheEnd = 1;
        int numberOfFits = Math.round(realVPArea / frame.getVerticalPillarDistance());
        int numberOfVPBothSides = (numberOfFits + VPAtTheEnd) * 2;
        return new MaterialLengthRecord(verticalPillar, numberOfVPBothSides);
    }
}
