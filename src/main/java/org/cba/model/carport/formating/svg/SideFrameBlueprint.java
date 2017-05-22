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
    private static final int CARPORT_X_OFFSET = 100;
    private FrameMaterialCalculator materialCalculator;
    private Frame frame;
    private int carportLength;

    public SideFrameBlueprint(FrameMaterialCalculator materialCalculator, int carportLength, Frame frame) {
        this.materialCalculator = materialCalculator;
        this.carportLength = carportLength;
        this.frame = frame;
    }

    @Override
    public void draw(SVGGraphics2D svgGen) {
        setScale(svgGen, carportLength);
        int yOffset;
        try {
            PartRecord upperPillars = materialCalculator.getSideUpperPillars();
            yOffset = drawUpperSidePillarAndGetYOffset(svgGen, upperPillars);

            PartRecord lowerPillars = materialCalculator.getLowerPillars();
            yOffset = drawLowerSidePillarAndGetYOffset(svgGen, yOffset, lowerPillars);

            PartRecord verticalPillars = materialCalculator.getVerticalPillars();
            drawVerticalPillars(svgGen, yOffset, verticalPillars);

            drawDistanceNoteVertically(svgGen, verticalPillars.getLength() + lowerPillars.getHeight(), upperPillars.getHeight(), -50);
            drawDistanceNoteVertically(svgGen, verticalPillars.getLength() + lowerPillars.getHeight() + upperPillars.getHeight(), 0, -20);
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int drawUpperSidePillarAndGetYOffset(SVGGraphics2D svgGen, PartRecord pillars) {
        svgGen.draw(new Rectangle(CARPORT_X_OFFSET, 0, carportLength, pillars.getHeight()));
        return pillars.getHeight();
    }

    private int drawLowerSidePillarAndGetYOffset(SVGGraphics2D svgGen, int yOffset, PartRecord pillars) throws MaterialLengthVariationNotFoundException {
        svgGen.draw(new Rectangle(CARPORT_X_OFFSET, yOffset, carportLength, pillars.getHeight()));
        return yOffset + pillars.getHeight();
    }

    private void drawVerticalPillars(Graphics2D svgGen, int yOffset, PartRecord pillars) {
        int amount = pillars.getCount() / 2;
        int distance = getDistanceOfVP(amount);
        int length = pillars.getLength();
        int xOffset = 0;
        int bottomNoteY = yOffset + length + 25;
        drawDistanceNoteHorizontally(svgGen, frame.getVerticalPillarFrontReserve(), CARPORT_X_OFFSET, bottomNoteY);
        for (int i = 0; i < amount; i++) {
            xOffset = CARPORT_X_OFFSET + frame.getVerticalPillarFrontReserve() + distance * i;
            svgGen.draw(new Rectangle(xOffset, yOffset, pillars.getWidth(), length));
            if (notTheLastVp(amount, i)) {
                drawDistanceNoteHorizontally(svgGen, distance, xOffset, bottomNoteY);
            }
        }
        drawDistanceNoteHorizontally(svgGen, frame.getVerticalPillarBackReserve(), xOffset, bottomNoteY);
        drawHelpingLines(svgGen, yOffset + length, carportLength + CARPORT_X_OFFSET);
    }

    private boolean notTheLastVp(int countOfVP, int i) {
        return i != countOfVP - 1;
    }

    private int getDistanceOfVP(int countOfVP) {
        int VPLengthArea = carportLength - frame.getVerticalPillarBackReserve() - frame.getVerticalPillarFrontReserve();
        return VPLengthArea / (countOfVP - 1);
    }

    private void drawHelpingLines(Graphics2D svgGen, int bottomY, int rightX) {
        svgGen.setColor(Color.RED);
        svgGen.drawLine(CARPORT_X_OFFSET, bottomY, rightX, bottomY);
        svgGen.drawLine(CARPORT_X_OFFSET, 0, CARPORT_X_OFFSET, bottomY);
        svgGen.drawLine(rightX, 0, rightX, bottomY);
        svgGen.setColor(Color.BLACK);
    }
}
