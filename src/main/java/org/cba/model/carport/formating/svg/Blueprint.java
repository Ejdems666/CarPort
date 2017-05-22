package org.cba.model.carport.formating.svg;

import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by adam on 21/05/2017.
 */
public abstract class Blueprint {
    protected void setScale(SVGGraphics2D svgGen, int svgWidth) {
        double renderedWidth = 500;
        double measurementsReserve = 100;
        double scale = renderedWidth / (svgWidth + measurementsReserve);
        svgGen.scale(scale,scale);
        svgGen.setSVGCanvasSize(new Dimension((int) renderedWidth,300));
    }

    protected void drawDistanceNoteVertically(Graphics2D svgGen, int distance, int x, int y) {
        AffineTransform orig = svgGen.getTransform();
        svgGen.rotate(Math.toRadians(90));
        drawDistanceNoteHorizontally(svgGen,distance,x,y);
        svgGen.setTransform(orig);
    }

    protected void drawDistanceNoteHorizontally(Graphics2D svgGen, int distance, int x, int y) {
        svgGen.drawString(String.valueOf(distance), x + (distance - 50) / 2, y);
        svgGen.drawLine(x, y, x + distance, y);
        svgGen.drawLine(x, y - 20, x, y + 20);
        svgGen.drawLine(x + distance, y - 20, x + distance, y + 20);
    }
}
