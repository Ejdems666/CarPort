package org.cba.model.carport.formating.pdf;

import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.cba.Path;
import org.cba.components.table.TableBuilder;
import org.cba.domain.Frame;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.MaterialCalculator;
import org.cba.model.carport.calculation.MaterialCalculatorFactory;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.AssemblyMaterialsTableFormatter;
import org.cba.model.carport.formating.PartRecord;
import org.cba.model.carport.formating.PartsFormatter;
import org.cba.model.carport.formating.PartsTableFormatter;
import org.cba.model.carport.formating.svg.BlueprintDrawing;
import org.cba.model.carport.formating.svg.BlueprintFactory;
import org.cba.model.carport.formating.svg.SVGGenerator;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by adam on 25/05/2017.
 */
public class TemporaryHtmlGenerator {
    public static final String TEMPORARY_HTML_FILE = "temp-pdf-html.html";
    private final MaterialCalculatorFactory factory = new MaterialCalculatorFactory();

    // TODO: JUST FOR TESTING, remove
    public static void main(String[] args) {
        PurchaseCarport purchase = PurchaseCarport.find.byId(1);
        TemporaryHtmlGenerator htmlGenerator = new TemporaryHtmlGenerator();
        ServletContext context = createNiceMock(ServletContext.class);
        expect(context.getRealPath(TEMPORARY_HTML_FILE)).andReturn("web/" + Path.GENERATING_PDF + TEMPORARY_HTML_FILE);
        replay(context);
        htmlGenerator.generateFile(purchase, context);
    }

    public File generateFile(PurchaseCarport purchase, ServletContext servletContext) {
        String filePath = servletContext.getRealPath(Path.GENERATING_PDF + TEMPORARY_HTML_FILE);
        File file = new File(filePath);
        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.print(
                    "<!DOCTYPE html>" +
                            "<html>" +
                            "<body>"
            );
            printTablesWithMaterials(purchase, writer);
            writer.print(getCarportSVG(purchase));
            writer.print(
                    "</body>" +
                            "</html>"
            );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    private String getCarportSVG(PurchaseCarport purchase) throws SVGGraphics2DIOException {
        Frame frame = purchase.getCarport().getFrame();
        BlueprintFactory factory = new BlueprintFactory();
        BlueprintDrawing blueprint = factory.getSideFrameBlueprint(frame,purchase);
        SVGGenerator svgGenerator = new SVGGenerator();
        return svgGenerator.generate(blueprint);
    }

    private void printTablesWithMaterials(PurchaseCarport purchase, PrintWriter writer) throws MaterialLengthVariationNotFoundException {
        PartsFormatter materialFormatter = new PartsTableFormatter("Materials", new TableBuilder("table"));
        PartsFormatter assemblyMaterialsFormatter = new AssemblyMaterialsTableFormatter("Assembly materials", new TableBuilder("table"));
        List<MaterialCalculator> calculators = factory.getMaterialCalculators(purchase.getCarport(), purchase);
        for (MaterialCalculator calculator : calculators) {
            for (PartRecord partRecord : calculator.getAllPartRecords()) {
                materialFormatter.addPartRecord(partRecord);
                assemblyMaterialsFormatter.addPartRecord(partRecord);
            }
        }
        writer.print(materialFormatter.getFormattedRecords());
        writer.print(assemblyMaterialsFormatter.getFormattedRecords());
    }
}
