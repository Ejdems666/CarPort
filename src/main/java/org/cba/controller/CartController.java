package org.cba.controller;

import org.cba.Path;
import org.cba.components.CarportSettingsForm;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.Carport;
import org.cba.model.carport.calculation.CarportSettings;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.pdf.PdfGenerator;
import org.cba.model.cart.IndexOfOrderNotFound;
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
            tableBuilder.addHeader(
                    "Current orders in cart",
                    "Carport name, Frame (width x length), Has shed, Shed Length, Price, Edit order, View pdf catalogue, Remove"
            );
            List<PurchaseCarport> purchaseCarports = cart.getCartContents().getPurchaseCarports();
            for (int i = 0; i < purchaseCarports.size(); i++) {
                PurchaseCarport purchaseCarport = purchaseCarports.get(i);
                Row row = tableBuilder.createNewRow();
                row.addColumn(purchaseCarport.getCarport().getName());
                row.addColumn(purchaseCarport.getFrameWidth() + " x " + purchaseCarport.getFrameLength());
                row.addColumn(purchaseCarport.isWithShed());
                row.addColumn(purchaseCarport.getShedLength());
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
        CarportSettingsForm.createFormComponents(request, order.getCarport().getFrame(), order);
        request.setAttribute("purchaseNumber", purchaseNumber);
        request.setAttribute("carport", order.getCarport());
        renderTemplate();
    }

    public void editConfirm(Integer purchaseNumber) {
        if (request.getMethod().equals("POST")) {
            try {
                CarportSettings settings = CarportSettingsForm.getRequestedCarportSettings(request);
                cart.editItem(purchaseNumber, settings);
                PurchaseCarport purchase = cart.getCartContents().getPurchaseCarports().get(purchaseNumber);
                regeneratePdfIfNonExistent(purchase);
                alertSuccess("Order changed.");
                redirect("cart");
                return;
            } catch (ParameterParserException e) {
                alertError("Wrong Input!");
            } catch (IndexOfOrderNotFound e) {
                alertError(e.getMessage());
            } catch (MaterialLengthVariationNotFoundException e) {
                alertError("Sorry, can't submit order with those frameDimensions.");
                redirect("cart/edit-confirm/" + purchaseNumber);
                return;
            }
        }
        renderTemplate("error/notFound");
    }

    private void regeneratePdfIfNonExistent(PurchaseCarport purchase) {
        if (purchase.getPdfCatalogue() != null) {
            PdfGenerator pdfGenerator = new PdfGenerator();
            pdfGenerator.generatePdf(purchase, request.getSession().getServletContext(), purchase.getPdfCatalogue());
        }
    }

    public void add(Integer carportId) {
        Carport carport = Carport.find.byId(carportId);
        if (carport == null) renderTemplate("error/notFound");
        try {
            CarportSettings settings = CarportSettingsForm.getRequestedCarportSettings(request, carport);
            cart.addItem(carport, settings);
            alertSuccess(carport.getName() + " added to cart");
            redirect("cart");
        } catch (ParameterParserException e) {
            alertError("Wrong Input!");
            renderTemplate("error/notFound");
        } catch (MaterialLengthVariationNotFoundException e) {
            alertError("Sorry, can't add carport with those frameDimensions.");
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
            return;
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
            return;
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
