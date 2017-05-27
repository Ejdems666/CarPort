package org.cba.model.carport.formating.pdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import org.cba.Path;
import org.cba.domain.PurchaseCarport;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by adam on 16/05/2017.
 */
public class PdfGenerator {

    public String generatePdf(PurchaseCarport purchase, ServletContext servletContext) {
        String fileName = purchase.getCarport().getName() + Calendar.getInstance().getTimeInMillis() + ".pdf";
        generatePdf(purchase,servletContext, fileName);
        return fileName;
    }

    public void generatePdf(PurchaseCarport purchase, ServletContext servletContext, String fileName) {
        TemporaryHtmlGenerator htmlGenerator = new TemporaryHtmlGenerator();
        File temporaryHtmlFile = htmlGenerator.generateFileAndGetPath(purchase, servletContext);
        String filePath = servletContext.getRealPath(Path.GENERATING_PDF + fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            try {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useSVGDrawer(new BatikSVGDrawer());
                builder.withFile(temporaryHtmlFile);
                builder.toStream(outputStream);
                builder.run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Opens connection in loop for created pdf, until the connection doesn't return 404 (pdf is created and accessible)
     * or until the loop runs n times
     */
    public void waitUntilThePdfIsAccessible(String fileName) {
        try {
            URL url = new URL(Path.URL + "" + Path.PDF + fileName);
            int safety = 0;
            int responseCode;
            do {
                if (safety++ == 20) break;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                responseCode = getResponseCodeOfRequestedPdfFile(url);
            } while (responseCode == 404);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getResponseCodeOfRequestedPdfFile(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getResponseCode();
    }
}
