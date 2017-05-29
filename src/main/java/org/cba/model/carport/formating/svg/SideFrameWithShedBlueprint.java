package org.cba.model.carport.formating.svg;

import org.cba.domain.Frame;
import org.cba.model.carport.calculation.ShedMaterialCalculator;
import org.cba.model.carport.formating.PartRecord;

import java.awt.*;

import static org.cba.model.carport.calculation.ShedMaterialCalculator.SHED_PLANK_OVERLAP;
import static org.cba.model.carport.calculation.ShedMaterialCalculator.SHED_RESERVE;

/**
 * Created by adam on 29/05/2017.
 */
public class SideFrameWithShedBlueprint extends SideFrameBlueprint {
    private int shedLength;
    private ShedMaterialCalculator materialCalculator;

    public SideFrameWithShedBlueprint(ShedMaterialCalculator materialCalculator, int frameLength, Frame frame, int shedLength) {
        super(materialCalculator, frameLength, frame);
        this.shedLength = shedLength;
        this.materialCalculator = materialCalculator;
    }

    @Override
    protected int getRegularVPArea(int countOfVP) {
        return frameLength
                - frame.getVerticalPillarBackReserve()
                - frame.getVerticalPillarFrontReserve()
                - shedLength
                - SHED_RESERVE;
    }

    @Override
    protected void drawAfterRegularVps(int RegularVpsEndX, int bottomMeasureY) {
        PartRecord shedPlanks = materialCalculator.getSideShedPlanks();
        int shedStartX = frameLength - shedLength - SHED_RESERVE + MEASURES_RESERVE;
        int yOffset = TOP_RESERVE + upperPillars.getWidth();
        drawDistanceBetweenShedAndRegularVps(RegularVpsEndX, bottomMeasureY, shedStartX);
        drawMeasureHorizontally(shedLength, shedStartX, bottomMeasureY);
        for (int i = 0; i < shedPlanks.getCount(); i++) {
            int offsetX = shedStartX + (shedPlanks.getWidth() - SHED_PLANK_OVERLAP) * i;
            svgGen.draw(new Rectangle(offsetX, yOffset, shedPlanks.getWidth(), shedPlanks.getLength()));
        }
        drawMeasureHorizontally(SHED_RESERVE, shedStartX + shedLength, bottomMeasureY);
    }

    private void drawDistanceBetweenShedAndRegularVps(int regularVpsEndX, int bottomMeasureY, int shedStartX) {
        int distance = Math.abs(shedStartX - regularVpsEndX);
        drawMeasureHorizontally(distance, regularVpsEndX, bottomMeasureY);
    }

    @Override
    protected PartRecord getImplementationSpecificVerticalPillars() {
        return materialCalculator.getRegularVerticalPillars();
    }
}
