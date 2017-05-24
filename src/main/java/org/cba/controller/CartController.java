package org.cba.controller;

import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.Carport;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;
import org.jetbrains.annotations.NotNull;

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
            tableBuilder.addHeader("Current orders in cart", "Carport name, Frame width, Frame length, Price, Link to edit order");
            List<PurchaseCarport> purchaseCarports = cart.getCartContents().getPurchaseCarports();
            for (int i = 0; i < purchaseCarports.size(); i++) {
                PurchaseCarport purchaseCarport = purchaseCarports.get(i);
                Row row = tableBuilder.createNewRow();
                row.addColumn(purchaseCarport.getCarport().getName());
                row.addColumn(purchaseCarport.getFrameWidth());
                row.addColumn(purchaseCarport.getFrameLength());
                row.addColumn(purchaseCarport.getPrice());
                row.addColumn(getEditLink(i));
            }
            request.setAttribute("table", tableBuilder);
        } else {
            request.setAttribute("table", "There are no orders in the cart right now.");
        }
        renderTemplate();
    }

    @NotNull
    private String getEditLink(int index) {
        return "<a href='" + ROOT + "cart/edit/" + index + "'>" + (index + 1) + "</a>";
    }

    public void edit(Integer index) {
        PurchaseCarport purchaseCarport = cart.getCartContents().getPurchaseCarports().get(index);
        request.setAttribute("purchaseCarport", purchaseCarport);
        renderTemplate();
    }

    public void add(Integer carportId) {
        Carport carport = Carport.find.byId(carportId);
        if (carport == null) renderTemplate("error/notFound");
        try {
            ParsedParameters parameters = getParametersForAddingOrder(carport);
            Dimensions frameDimensions = new Dimensions(parameters.getInteger("frameLength"), parameters.getInteger("frameWidth"));
            cart.addItem(carport, frameDimensions);
            redirect("cart");
        } catch (ParameterParserException e) {
            alertError("Wrong Input!");
            renderTemplate("error/notFound");
        } catch (MaterialLengthVariationNotFoundException e) {
            alertError("Sorry, can't add carport with that dimensions");
            redirect("carport/edit/" + carportId);
        }
    }

    private ParsedParameters getParametersForAddingOrder(Carport carport) throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addInteger("frameWidth").setDefaultValue(carport.getDefaultWidth());
        parameterFilter.addInteger("frameLength").setDefaultValue(carport.getDefaultLength());
        return parameterFilter.parseParameters(request);
    }
}
