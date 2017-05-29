package org.cba.controller;

import io.ebean.Ebean;
import org.cba.Path;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.controller.exception.NonExistentResourceException;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.facade.PdfFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by adam on 29/05/2017.
 */
public class OrderController extends BaseController {

    public OrderController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void history() {
        if (redirectIfNotSignedIn()) return;
        List<Purchase> purchases = loggedUser.getPurchaseList();
        if (purchases.size() > 0) {
            TableBuilder tableBuilder = new TableBuilder("table");
            tableBuilder.addHeader("Order history","Carport name, Price, Date, PDF catalogue");
            for (Purchase purchase : purchases) {
                for (PurchaseCarport purchaseCarport : purchase.getPurchaseCarports()) {
                    Row row = tableBuilder.createNewRow();
                    row.addColumn(purchaseCarport.getCarport().getName());
                    row.addColumn(purchaseCarport.getPrice());
                    row.addColumn(purchase.getOrderedOn());
                    row.addColumnLink("order/pdf/" + purchaseCarport.getId(), Row.Icon.PDF);
                }
            }
            request.setAttribute("table",tableBuilder);
        }
        renderTemplate();
    }

    public void pdf(Integer purchaseId) {
        PurchaseCarport purchase = PurchaseCarport.find.byId(purchaseId);
        try {
            if (purchase == null) throw new NonExistentResourceException("CarportPurchase", purchaseId);
            PdfFacade facade = new PdfFacade(request.getSession().getServletContext());
            facade.generatePdf(purchase);
            Ebean.save(purchase);
            redirect(Path.PDF, purchase.getPdfCatalogue());
        } catch (NonExistentResourceException e) {
            renderTemplate("error/NotFound");
        }
    }
}
