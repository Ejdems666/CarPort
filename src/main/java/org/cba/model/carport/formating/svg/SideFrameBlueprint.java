package org.cba.model.carport.formating.svg;

import org.apache.batik.svggen.SVGGraphics2D;
import org.cba.domain.Frame;
import org.cba.model.carport.calculation.FrameMaterialCalculator;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartRecord;

import java.awt.*;


/**
 * Created by adam on 21/05/2017.
 */
public abstract class SideFrameBlueprint extends Blueprint implements BlueprintDrawing {
    protected Frame frame;
    protected int frameLength;
    protected PartRecord verticalPillars;
    protected PartRecord upperPillars;
    protected PartRecord lowerPillars;
    private FrameMaterialCalculator materialCalculator;

    public SideFrameBlueprint(FrameMaterialCalculator materialCalculator, int frameLength, Frame frame) {
        this.materialCalculator = materialCalculator;
        this.frameLength = frameLength;
        this.frame = frame;
    }

    @Override
    public void draw(SVGGraphics2D svgGen) {
        super.svgGen = svgGen;
        try {
            upperPillars = materialCalculator.getSideUpperPillars();
            lowerPillars = materialCalculator.getLowerPillars();
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace(); // Will not happen in this stage
        }
        verticalPillars = getImplementationSpecificVerticalPillars();

        int upperPillarBottomY = TOP_RESERVE + upperPillars.getWidth();
        int carportHeight = upperPillarBottomY +
                lowerPillars.getWidth() +
                verticalPillars.getLength();
        setScale(frameLength, carportHeight);
        drawHelpingLines(carportHeight, frameLength + MEASURES_RESERVE);

        drawUpperSidePillar();
        drawLowerSidePillar(upperPillarBottomY);
        int lowerPillarBottomY = upperPillarBottomY + lowerPillars.getWidth();
        drawRegularVerticalPillars(lowerPillarBottomY);
    }

    protected abstract PartRecord getImplementationSpecificVerticalPillars();

    private void drawHelpingLines(int bottomY, int rightX) {
        svgGen.setColor(Color.GRAY);
        svgGen.drawLine(MEASURES_RESERVE, TOP_RESERVE, MEASURES_RESERVE, bottomY); // left
        svgGen.drawLine(MEASURES_RESERVE, bottomY, rightX, bottomY); // bottom
        svgGen.drawLine(rightX, TOP_RESERVE, rightX, bottomY); // right
        svgGen.setColor(Color.BLACK);
    }

    private void drawUpperSidePillar() {
        svgGen.draw(new Rectangle(MEASURES_RESERVE, TOP_RESERVE, frameLength, upperPillars.getWidth()));
        drawMeasureVertically(
                verticalPillars.getLength() + lowerPillars.getWidth() + upperPillars.getWidth(),
                TOP_RESERVE,
                -20
        );
    }

    private void drawLowerSidePillar(int yOffset) {
        svgGen.draw(new Rectangle(MEASURES_RESERVE, yOffset, frameLength, lowerPillars.getWidth()));
        drawMeasureVertically(
                verticalPillars.getLength() + lowerPillars.getWidth(),
                upperPillars.getWidth() + TOP_RESERVE,
                -50
        );
    }

    private void drawRegularVerticalPillars(int yOffset) {
        int length = verticalPillars.getLength();
        int xOffset = MEASURES_RESERVE;
        int bottomMeasureY = yOffset + length + MEASURES_RESERVE / 2;
        int count = verticalPillars.getCount() / 2;
        if (count != 0) {
            drawMeasureHorizontally(frame.getVerticalPillarFrontReserve(), MEASURES_RESERVE, bottomMeasureY);
            int distance = getDistance(count);
            for (int i = 0; i < count; i++) {
                xOffset = MEASURES_RESERVE + frame.getVerticalPillarFrontReserve() + distance * i;
                svgGen.draw(new Rectangle(xOffset, yOffset, verticalPillars.getWidth(), length));
                if (notTheLastVp(count, i)) {
                    drawMeasureHorizontally(distance, xOffset, bottomMeasureY);
                }
            }
        }
        drawAfterRegularVps(xOffset, bottomMeasureY);
    }

    private int getDistance(int count) {
        int realVPArea = getRegularVPArea(count);
        if (count > 1) {
            return realVPArea / (count - 1);
        }
        return realVPArea;
    }

    private boolean notTheLastVp(int countOfVP, int i) {
        return i != countOfVP - 1;
    }

    protected abstract int getRegularVPArea(int countOfVP);

    protected abstract void drawAfterRegularVps(int xOffset, int bottomMeasureY);
}
