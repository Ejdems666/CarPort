package org.cba.model.facade;

import org.cba.Path;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.formating.pdf.PdfGenerator;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by adam on 29/05/2017.
 */
public class PdfFacade {
    private ServletContext servletContext;

    public PdfFacade(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void generatePdf(PurchaseCarport purchase) {
        if (purchase.getPdfCatalogue() == null || pdfCatalogueFileDoesNotExist(purchase.getPdfCatalogue())) {
            PdfGenerator pdfGenerator = new PdfGenerator();
            String fileName = pdfGenerator.generatePdf(purchase, servletContext);
            pdfGenerator.waitUntilThePdfIsAccessible(fileName);
            purchase.setPdfCatalogue(fileName);
        }
    }

    private boolean pdfCatalogueFileDoesNotExist(String fileName) {
        File file = new File(servletContext.getRealPath(Path.GENERATING_PDF + fileName));
        return !file.exists();
    }
}
