package org.cba.controller;

import org.cba.Path;
import org.cba.components.CarportEditForm;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.Carport;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.pdf.PdfGenerator;
import org.cba.model.cart.IndexOfOrderNotFound;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Created by adam on 23/05/2017.
 */
public class CartController extends BaseController {
    public CartController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void index() {
        if (cart.getNumberOfItems() > 0) {
            TableBuilder tableBuilder = new TableBuilder("table");
            tableBuilder.addHeader("Current orders in cart", "Carport name, Frame width, Frame length, Price, Edit order, View pdf catalogue, Remove");
            List<PurchaseCarport> purchaseCarports = cart.getCartContents().getPurchaseCarports();
            for (int i = 0; i < purchaseCarports.size(); i++) {
                PurchaseCarport purchaseCarport = purchaseCarports.get(i);
                Row row = tableBuilder.createNewRow();
                row.addColumn(purchaseCarport.getCarport().getName());
                row.addColumn(purchaseCarport.getFrameWidth());
                row.addColumn(purchaseCarport.getFrameLength());
                row.addColumn(purchaseCarport.getPrice());
                row.addColumnLink("cart/edit/" + i, Row.Icon.EDIT);
                row.addColumnLink("cart/pdf/" + i, Row.Icon.PDF);
                row.addColumnLink("cart/delete/" + i, Row.Icon.DELETE);
            }
            request.setAttribute("table", tableBuilder);
        } else {
            request.setAttribute("noOrders", "There are no orders in the cart right now.");
        }
        renderTemplate();
    }

    public void edit(Integer purchaseNumber) {
        PurchaseCarport order = null;
        try {
            order = cart.getItem(purchaseNumber);
        } catch (IndexOfOrderNotFound e) {
            alertError(e.getMessage());
            renderTemplate("error/notFound");
        }
        CarportEditForm.createAndPassSelectComponents(request, order.getCarport(), order.getFrameDimensions());
        request.setAttribute("purchaseNumber", purchaseNumber);
        request.setAttribute("carport", order.getCarport());
        renderTemplate();
    }

    // TODO: regenerate pdf if exists
    public void editConfirm(Integer purchaseNumber) {
        if (request.getMethod().equals("POST")) {
            PurchaseCarport purchase = cart.getCartContents().getPurchaseCarports().get(purchaseNumber);
            try {
                ParsedParameters parameters = getDimensionsParameters(purchase.getCarport());
                Dimensions frameDimensions = new Dimensions(parameters.getInteger("frameLength"), parameters.getInteger("frameWidth"));
                purchase.setFrameDimensions(frameDimensions);
                cart.recalculatePriceForItem(purchaseNumber);
                regeneratePdfCatalogue(purchase);
                alertSuccess("Order changed.");
                redirect("cart");
            } catch (ParameterParserException e) {
                alertError("Wrong Input!");
            } catch (IndexOfOrderNotFound e) {
                alertError(e.getMessage());
            } catch (MaterialLengthVariationNotFoundException e) {
                alertError("Sorry, can't submit order with those dimensions.");
                redirect("cart/edit-confirm/" + purchaseNumber);
            }
        }
        renderTemplate("error/notFound");
    }

    private void regeneratePdfCatalogue(PurchaseCarport purchase) {
        if (purchase.getPdfCatalogue() != null) {
            PdfGenerator pdfGenerator = new PdfGenerator();
            pdfGenerator.generatePdf(purchase, request.getSession().getServletContext(), purchase.getPdfCatalogue());
        }
    }

    private ParsedParameters getDimensionsParameters(Carport carport) throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addInteger("frameWidth").setDefaultValue(carport.getDefaultWidth());
        parameterFilter.addInteger("frameLength").setDefaultValue(carport.getDefaultLength());
        return parameterFilter.parseParameters(request);
    }

    public void add(Integer carportId) {
        Carport carport = Carport.find.byId(carportId);
        if (carport == null) renderTemplate("error/notFound");
        try {
            ParsedParameters parameters = getDimensionsParameters(carport);
            Dimensions frameDimensions = new Dimensions(parameters.getInteger("frameLength"), parameters.getInteger("frameWidth"));
            cart.addItem(carport, frameDimensions);
            alertSuccess(carport.getName() + " added to cart");
            redirect("cart");
        } catch (ParameterParserException e) {
            alertError("Wrong Input!");
            renderTemplate("error/notFound");
        } catch (MaterialLengthVariationNotFoundException e) {
            alertError("Sorry, can't add carport with those dimensions.");
            redirect("carport/edit/" + carportId);
        }
    }

    public void delete(Integer purchaseNumber) {
        try {
            cart.removeItem(purchaseNumber);
            alertSuccess("Order removed.");
        } catch (IndexOfOrderNotFound e) {
            alertError(e.getMessage());
        }
        redirect("cart");
    }

    public void buy() {
        if (cart.getNumberOfItems() == 0) {
            alertError("You can't buy anything, because the cart is empty!");
            redirect("carport/all");
        }
        Purchase purchase = cart.getCartContents();
        if (isLoggedIn()) {
            purchase.setCustomer(loggedUser);
        }
        cart.saveInDatabaseAndEmptyCart();
        renderTemplate();
    }

    public void pdf(Integer purchaseNumber) {
        try {
            PurchaseCarport purchase = cart.getItem(purchaseNumber);
            if (purchase.getPdfCatalogue() == null || pdfCatalogueFileDoesNotExist(purchase.getPdfCatalogue())) {
                PdfGenerator pdfGenerator = new PdfGenerator();
                String fileName = pdfGenerator.generatePdf(purchase, request.getSession().getServletContext());
                pdfGenerator.waitUntilThePdfIsAccessible(fileName);
                purchase.setPdfCatalogue(fileName);
            }
            redirect(Path.PDF, purchase.getPdfCatalogue());
        } catch (IndexOfOrderNotFound e) {
            alertError(e.getMessage());
            renderTemplate("error/notFound");
        }
    }

    private boolean pdfCatalogueFileDoesNotExist(String fileName) {
        ServletContext servletContext = request.getSession().getServletContext();
        File file = new File(servletContext.getRealPath(Path.GENERATING_PDF + fileName));
        return !file.exists();
    }
}
