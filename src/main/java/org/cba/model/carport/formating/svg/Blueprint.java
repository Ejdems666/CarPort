package org.cba.model.carport.formating.svg;

import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by adam on 21/05/2017.
 */
public abstract class Blueprint {
    protected static final int TOP_RESERVE = 20;
    protected static final int MEASURES_RESERVE = 100;
    private final double CANVAS_WIDTH = 600;
    private final double CANVAS_HEIGHT = 300;
    protected SVGGraphics2D svgGen;

    protected void setScale(int svgWidth, int svgHeight) {
        double widthScale = CANVAS_WIDTH / (svgWidth + MEASURES_RESERVE);
        double heightScale = CANVAS_HEIGHT / (svgHeight + MEASURES_RESERVE);
        svgGen.scale(widthScale, heightScale);
        svgGen.setSVGCanvasSize(new Dimension((int) CANVAS_WIDTH, (int) CANVAS_HEIGHT));
    }

    protected void drawMeasureVertically(int distance, int x, int y) {
        AffineTransform orig = svgGen.getTransform();
        svgGen.rotate(Math.toRadians(90));
        drawMeasureHorizontally(distance, x, y);
        svgGen.setTransform(orig);
    }

    protected void drawMeasureHorizontally(int distance, int x, int y) {
        int text_margin = 5;
        svgGen.drawString(String.valueOf(distance), x + (distance - 50) / 2, y - text_margin);
        svgGen.drawLine(x, y, x + distance, y);
        int border_height = 20;
        svgGen.drawLine(x, y - border_height, x, y + border_height);
        svgGen.drawLine(x + distance, y - border_height, x + distance, y + border_height);
    }
}
