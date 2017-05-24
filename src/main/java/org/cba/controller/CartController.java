package org.cba.controller;

import org.cba.components.CarportEditForm;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.Carport;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.cart.IndexOfOrderNotFound;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            tableBuilder.addHeader("Current orders in cart", "Carport name, Frame width, Frame length, Price, Link to edit order, Remove");
            List<PurchaseCarport> purchaseCarports = cart.getCartContents().getPurchaseCarports();
            for (int i = 0; i < purchaseCarports.size(); i++) {
                PurchaseCarport purchaseCarport = purchaseCarports.get(i);
                Row row = tableBuilder.createNewRow();
                row.addColumn(purchaseCarport.getCarport().getName());
                row.addColumn(purchaseCarport.getFrameWidth());
                row.addColumn(purchaseCarport.getFrameLength());
                row.addColumn(purchaseCarport.getPrice());
                row.addColumnLink(ROOT + "cart/edit/" + i, Row.Icon.EDIT);
                row.addColumnLink(ROOT + "cart/delete/" + i, Row.Icon.DELETE);
            }
            request.setAttribute("table", tableBuilder);
        } else {
            request.setAttribute("noOrders", "There are no orders in the cart right now.");
        }
        renderTemplate();
    }

    public void edit(Integer orderNumber) {
        PurchaseCarport order = null;
        try {
            order = cart.getItem(orderNumber);
        } catch (IndexOfOrderNotFound e) {
            alertError(e.getMessage());
            renderTemplate("error/notFound");
        }
        CarportEditForm form = new CarportEditForm(request);
        form.createAndPassSelectComponents(order.getCarport(),order.getFrameDimensions());
        request.setAttribute("orderNumber", orderNumber);
        request.setAttribute("carport", order.getCarport());
        renderTemplate();
    }

    public void editConfirm(Integer orderNumber) {
        if (request.getMethod().equals("POST")) {
            PurchaseCarport purchaseCarport = cart.getCartContents().getPurchaseCarports().get(orderNumber);
            try {
                ParsedParameters parameters = getDimensionsParameters(purchaseCarport.getCarport());
                Dimensions frameDimensions = new Dimensions(parameters.getInteger("frameLength"), parameters.getInteger("frameWidth"));
                purchaseCarport.setFrameDimensions(frameDimensions);
                cart.recalculatePriceForItem(orderNumber);
                alertSuccess("Order changed.");
                redirect("cart");
            } catch (ParameterParserException e) {
                alertError("Wrong Input!");
            } catch (IndexOfOrderNotFound e) {
                alertError(e.getMessage());
            } catch (MaterialLengthVariationNotFoundException e) {
                alertError("Sorry, can't submit order with those dimensions.");
                redirect("carport/edit-confirm/" + orderNumber);
            }
        }
        renderTemplate("error/notFound");
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

    public void remove(Integer index) {
        try {
            cart.removeItem(index);
            alertSuccess("Order removed.");
        } catch (IndexOfOrderNotFound e) {
            alertError(e.getMessage());
        }
        redirect("cart");
    }
}
