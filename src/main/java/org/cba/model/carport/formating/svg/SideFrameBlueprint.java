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
    private int yOffset = 0;

    public SideFrameBlueprint(FrameMaterialCalculator materialCalculator, int carportLength, Frame frame) {
        this.materialCalculator = materialCalculator;
        this.carportLength = carportLength;
        this.frame = frame;
    }

    @Override
    public void draw(SVGGraphics2D svgGen) {
        svgGen.setStroke(new BasicStroke(2));
        setScale(svgGen, carportLength);
        int yOffset = 0;
        try {
            yOffset = drawUpperSidePillarAndGetYOffset(svgGen);
            yOffset = drawLowerSidePillarAndGetYOffset(svgGen, yOffset);
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
        drawVerticalPillars(svgGen, yOffset);
    }

    private int drawUpperSidePillarAndGetYOffset(SVGGraphics2D svgGen) throws MaterialLengthVariationNotFoundException {
        PartRecord pillars = materialCalculator.getSideUpperPillars();
        svgGen.draw(new Rectangle(0, yOffset, carportLength, pillars.getHeight()));
        return pillars.getHeight();
    }

    private int drawLowerSidePillarAndGetYOffset(SVGGraphics2D svgGen, int yOffset) throws MaterialLengthVariationNotFoundException {
        PartRecord pillars = materialCalculator.getLowerPillars();
        svgGen.draw(new Rectangle(0, yOffset, carportLength, pillars.getHeight()));
        return yOffset + pillars.getHeight();
    }

    protected void drawDistanceNoteVertically(Graphics2D svgGen, int distance, int x, int y) {
        svgGen.drawString(String.valueOf(distance), x + (distance - 50) / 2, y);
        svgGen.drawLine(x, y, x + distance, y);
        svgGen.drawLine(x, y - 20, x, y + 20);
        svgGen.drawLine(x + distance, y - 20, x + distance, y + 20);
    }

    private void drawVerticalPillars(Graphics2D svgGen, int yOffset) {
        Font font = new Font("Verdana", Font.BOLD, 20);
        svgGen.setFont(font);
        PartRecord pillars = materialCalculator.getVerticalPillars();
        int amount = pillars.getCount() / 2;
        int distance = getDistanceOfVP(amount);
        int length = pillars.getLength();
        int pillarsXOffset = 0;
        int bottomNoteY = yOffset + length + 25;
        drawDistanceNoteHorizontally(svgGen, frame.getVerticalPillarFrontReserve(), pillarsXOffset, bottomNoteY);
        for (int i = 0; i < amount; i++) {
            pillarsXOffset = frame.getVerticalPillarFrontReserve() + distance * i;
            svgGen.draw(new Rectangle(pillarsXOffset, yOffset, pillars.getWidth(), length));
            if (notTheLastVp(amount, i)) {
                drawDistanceNoteHorizontally(svgGen, distance, pillarsXOffset, bottomNoteY);
            }
        }
        drawDistanceNoteHorizontally(svgGen, frame.getVerticalPillarBackReserve(), pillarsXOffset, bottomNoteY);
        drawHelpingLines(svgGen, yOffset + length, carportLength);
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
        svgGen.drawLine(0, bottomY, rightX, bottomY);
        svgGen.drawLine(0, 0, 0, bottomY);
        svgGen.drawLine(rightX, 0, rightX, bottomY);
    }
}
