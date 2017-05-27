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
public class SideFrameBlueprint extends Blueprint implements BlueprintDrawing {
    private FrameMaterialCalculator materialCalculator;
    private Frame frame;
    private int carportLength;
    private PartRecord verticalPillars;
    private PartRecord upperPillars;
    private PartRecord lowerPillars;

    public SideFrameBlueprint(FrameMaterialCalculator materialCalculator, int carportLength, Frame frame) {
        this.materialCalculator = materialCalculator;
        this.carportLength = carportLength;
        this.frame = frame;
    }

    @Override
    public void draw(SVGGraphics2D svgGen) {
        super.svgGen = svgGen;
        try {
            verticalPillars = materialCalculator.getVerticalPillars();
            upperPillars = materialCalculator.getSideUpperPillars();
            lowerPillars = materialCalculator.getLowerPillars();

            int upperPillarBottomY = TOP_RESERVE + upperPillars.getHeight();
            int carportHeight = upperPillarBottomY +
                    lowerPillars.getHeight() +
                    verticalPillars.getLength();
            setScale(carportLength, carportHeight);
            drawHelpingLines(carportHeight, carportLength + MEASURES_RESERVE);

            drawUpperSidePillar();
            drawLowerSidePillar(upperPillarBottomY);
            int lowerPillarBottomY = upperPillarBottomY + lowerPillars.getHeight();
            drawVerticalPillars(lowerPillarBottomY);

        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void drawHelpingLines(int bottomY, int rightX) {
        svgGen.setColor(Color.GRAY);
        svgGen.drawLine(MEASURES_RESERVE, TOP_RESERVE, MEASURES_RESERVE, bottomY); // left
        svgGen.drawLine(MEASURES_RESERVE, bottomY, rightX, bottomY); // bottom
        svgGen.drawLine(rightX, TOP_RESERVE, rightX, bottomY); // right
        svgGen.setColor(Color.BLACK);
    }

    private void drawUpperSidePillar() {
        svgGen.draw(new Rectangle(MEASURES_RESERVE, TOP_RESERVE, carportLength, upperPillars.getHeight()));
        drawMeasureVertically(
                verticalPillars.getLength() + lowerPillars.getHeight() + upperPillars.getHeight(),
                TOP_RESERVE,
                -20
        );
    }

    private void drawLowerSidePillar(int yOffset) {
        svgGen.draw(new Rectangle(MEASURES_RESERVE, yOffset, carportLength, lowerPillars.getHeight()));
        drawMeasureVertically(
                verticalPillars.getLength() + lowerPillars.getHeight(),
                upperPillars.getHeight() + TOP_RESERVE,
                -50
        );
    }

    private void drawVerticalPillars(int yOffset) {
        int amount = verticalPillars.getCount() / 2;
        int distance = getDistanceOfVP(amount);
        int length = verticalPillars.getLength();
        int xOffset = 0;
        int bottomMeasureY = yOffset + length + MEASURES_RESERVE / 2;
        drawMeasureHorizontally(frame.getVerticalPillarFrontReserve(), MEASURES_RESERVE, bottomMeasureY);
        for (int i = 0; i < amount; i++) {
            xOffset = MEASURES_RESERVE + frame.getVerticalPillarFrontReserve() + distance * i;
            svgGen.draw(new Rectangle(xOffset, yOffset, verticalPillars.getWidth(), length));
            if (notTheLastVp(amount, i)) {
                drawMeasureHorizontally(distance, xOffset, bottomMeasureY);
            }
        }
        drawMeasureHorizontally(frame.getVerticalPillarBackReserve(), xOffset, bottomMeasureY);
    }

    private boolean notTheLastVp(int countOfVP, int i) {
        return i != countOfVP - 1;
    }

    private int getDistanceOfVP(int countOfVP) {
        int VPLengthArea = carportLength - frame.getVerticalPillarBackReserve() - frame.getVerticalPillarFrontReserve();
        return VPLengthArea / (countOfVP - 1);
    }
}
