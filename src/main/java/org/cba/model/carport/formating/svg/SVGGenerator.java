package org.cba.model.carport.formating.svg;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.cba.domain.Frame;
import org.cba.model.carport.calculation.BareFrameMaterialCalculator;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by adam on 14/05/2017.
 */
public class SVGGenerator {

    public String generate(BlueprintDrawing blueprintDrawing) throws SVGGraphics2DIOException {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument("", "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        blueprintDrawing.draw(svgGenerator);

        Writer out = new StringWriter();
        boolean useCss = true;
        svgGenerator.stream(out, useCss);
        return removeNonSVGTags(out);
    }

    @NotNull
    private String removeNonSVGTags(Writer out) {
        String result = out.toString();
        result = result.substring(result.indexOf("<svg"));
        return result;
    }

    public static void main(String[] args) throws SVGGraphics2DIOException {
        SVGGenerator svgGenerator = new SVGGenerator();
        int desiredLength = 800;
        Frame frame = Frame.find.byId(1);
        BlueprintDrawing blueprint = new SideFrameBlueprint(
                new BareFrameMaterialCalculator(frame,400,desiredLength), desiredLength, frame
        );
        String svg = svgGenerator.generate(blueprint);
        System.out.println(svg);
    }
}
