package org.cba.model.carport.formating.svg;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.cba.domain.Frame;
import org.cba.model.carport.calculation.BareFrameMaterialCalculator;
import org.cba.model.carport.calculation.Dimensions;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by adam on 14/05/2017.
 */
public class SVGGenerator {

    public String generate(BlueprintDrawing blueprintDrawing) throws SVGGraphics2DIOException {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
        ctx.setEmbeddedFontsOn(true);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(ctx, true);
        setStrokeAndFont(svgGenerator);
        blueprintDrawing.draw(svgGenerator);

        Writer out = new StringWriter();
        boolean useCss = true;
        svgGenerator.stream(out, useCss);
        return removeNonSVGTags(out);
    }

    private void setStrokeAndFont(SVGGraphics2D svgGenerator) {
        svgGenerator.setStroke(new BasicStroke(2));
        svgGenerator.setFont(new Font("Verdana", Font.BOLD, 20));
    }

    @NotNull
    private String removeNonSVGTags(Writer out) {
        String result = out.toString();
        result = result.substring(result.indexOf("<svg"));
        return result;
    }

    public static void main(String[] args) throws SVGGraphics2DIOException {
        SVGGenerator svgGenerator = new SVGGenerator();
        Frame frame = Frame.find.byId(1);
        Dimensions carportDimension = new Dimensions(800,400);
        BlueprintDrawing blueprint = new SideFrameBlueprint(
                new BareFrameMaterialCalculator(frame,carportDimension), carportDimension.length, frame
        );
        String svg = svgGenerator.generate(blueprint);
        System.out.println(svg);
    }
}
