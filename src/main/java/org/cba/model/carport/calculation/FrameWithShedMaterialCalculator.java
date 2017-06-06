package org.cba.model.carport.calculation;

import org.cba.domain.Frame;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.cba.model.carport.formating.PartRecord;

import java.util.List;

/**
 * Created by adam on 28/05/2017.
 */
public class FrameWithShedMaterialCalculator extends AbstractFrameMaterialCalculator implements ShedMaterialCalculator {
    private Dimensions shedDimensions;

    public FrameWithShedMaterialCalculator(Frame frame, Dimensions frameDimensions, Dimensions shedDimensions) {
        super(frame, frameDimensions);
        this.shedDimensions = shedDimensions;
    }

    @Override
    public PartRecord getShedPlanks() {
        MaterialLength shedPlanks = frame.getShedPlankMaterial().getMaterialLengths().get(0);
        int numberOfPlanks = getNumberOfShedPlanks(shedPlanks, shedDimensions.length) * 2;
        numberOfPlanks += getNumberOfShedPlanks(shedPlanks, shedDimensions.width) * 2;
        return new MaterialLengthRecord(shedPlanks, numberOfPlanks);
    }

    private int getNumberOfShedPlanks(MaterialLength shedPlanks, int area) {
        return area / (shedPlanks.getMaterial().getWidth() - SHED_PLANK_OVERLAP);
    }

    @Override
    public PartRecord getSideShedPlanks() {
        MaterialLength shedPlanks = frame.getShedPlankMaterial().getMaterialLengths().get(0);
        int numberOfShedPlanksOnSide = getNumberOfShedPlanks(shedPlanks, shedDimensions.length);
        return new MaterialLengthRecord(shedPlanks, numberOfShedPlanksOnSide);
    }

    @Override
    public PartRecord getSideShedVerticalPillars() {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfVPsOnBothSides = getNumberOfShedVPsOnSides();
        return new MaterialLengthRecord(verticalPillar, numberOfVPsOnBothSides);
    }

    private int getNumberOfShedVPsOnSides() {
        int VPAtTheEnd = 1;
        int numberOfVpsOnOneSide = shedDimensions.length / frame.getVerticalPillarDistance() + VPAtTheEnd;
        return numberOfVpsOnOneSide * 2;
    }

    @Override
    public PartRecord getRegularVerticalPillars() {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfVPsOnBothSides = getNumberOfRegularVPs();
        return new MaterialLengthRecord(verticalPillar, numberOfVPsOnBothSides);
    }

    private int getNumberOfRegularVPs() {
        int regularVPArea = frameDimensions.length
                - shedDimensions.length
                - SHED_RESERVE
                - frame.getVerticalPillarFrontReserve()
                - frame.getVerticalPillarBackReserve();
        if (regularVPArea < frame.getVerticalPillarFrontReserve()) {
            return 0;
        }
        int VPAtTheEnd = 1;
        int numberOFVpsOnOneSide = Math.round(regularVPArea / frame.getVerticalPillarDistance()) + VPAtTheEnd;
        return numberOFVpsOnOneSide * 2;
    }

    @Override
    public PartRecord getVerticalPillars() {
        MaterialLength verticalPillar = frame.getVerticalPillarMaterial().getMaterialLengths().get(0);
        int numberOfRegularVPs = getNumberOfRegularVPs();
        int twoCenterShedVPs = 2;
        int numberOfAllVPs = numberOfRegularVPs + getNumberOfShedVPsOnSides() + twoCenterShedVPs;
        return new MaterialLengthRecord(verticalPillar, numberOfAllVPs);
    }

    @Override
    public List<PartRecord> getAllPartRecords() throws MaterialLengthVariationNotFoundException {
        List<PartRecord> partRecords = super.getAllPartRecords();
        partRecords.add(getShedPlanks());
        return partRecords;
    }
}
