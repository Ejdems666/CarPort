package org.cba.model.carport.formating.svg;

import org.cba.domain.Frame;
import org.cba.model.carport.calculation.FrameMaterialCalculator;
import org.cba.model.carport.formating.PartRecord;

/**
 * Created by adam on 29/05/2017.
 */
public class SideBareFrameBlueprint extends SideFrameBlueprint {
    private FrameMaterialCalculator materialCalculator;

    public SideBareFrameBlueprint(FrameMaterialCalculator materialCalculator, int frameLength, Frame frame) {
        super(materialCalculator, frameLength, frame);
        this.materialCalculator = materialCalculator;
    }

    @Override
    protected PartRecord getImplementationSpecificVerticalPillars() {
        return materialCalculator.getVerticalPillars();
    }

    @Override
    protected int getRegularVPArea(int countOfVP) {
        return frameLength - frame.getVerticalPillarBackReserve() - frame.getVerticalPillarFrontReserve();
    }

    @Override
    protected void drawAfterRegularVps(int xOffset, int bottomMeasureY) {
        drawMeasureHorizontally(frame.getVerticalPillarBackReserve(), xOffset, bottomMeasureY);
    }
}
