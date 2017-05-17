package org.cba.model.pdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;

import java.awt.print.PrinterException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by adam on 16/05/2017.
 */
public class PdfPrinter {

    public static void main(String args[]) throws PrinterException, IOException {
        OutputStream os = null;
        String out = "/Users/adam/IdeaProjects/CarPort/test.pdf";
        String url = "file:///Users/adam/IdeaProjects/CarPort/test-svg.xhtml";
        try {
            os = new FileOutputStream(out);

            try {
                // There are more options on the builder than shown below.
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useSVGDrawer(new BatikSVGDrawer());
                builder.withUri(url);
                builder.toStream(os);
                builder.run();

            } catch (Exception e) {
                e.printStackTrace();
                // LOG exception
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                    // swallow
                }
            }
        }
        catch (IOException e1) {
            e1.printStackTrace();
            // LOG exception.
        }
    }
}
