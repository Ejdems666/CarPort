package org.cba.model.cart;

import org.cba.domain.Carport;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.model.carport.calculation.PriceCalculator;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;

import javax.servlet.http.HttpSession;

/**
 * Created by adam on 23/05/2017.
 * Manages cart storage in session, recalculates final price when cart contents are changed
 */
public class SessionCart implements Cart {
    private final String SESSION_IDENTIFIER = "cart";
    private final HttpSession session;
    private Purchase cartContents;
    private PriceCalculator priceCalculator = new PriceCalculator();

    public SessionCart(HttpSession session) {
        if (session.getAttribute(SESSION_IDENTIFIER) != null) {
            cartContents = ((Purchase) session.getAttribute(SESSION_IDENTIFIER));
        } else {
            cartContents = new Purchase();
            session.setAttribute(SESSION_IDENTIFIER, cartContents);
        }
        this.session = session;
    }

    @Override
    public void addItem(Carport carport, Dimensions frameDimensions) throws MaterialLengthVariationNotFoundException {
        int newItemPrice = priceCalculator.getPrice(carport, frameDimensions);
        cartContents.addPurchaseCarport(new PurchaseCarport(carport, frameDimensions, newItemPrice));
        cartContents.setFinalPrice(newItemPrice + cartContents.getFinalPrice());
    }

    @Override
    public void removeItem(int index) {
        PurchaseCarport removedItem = cartContents.getPurchaseCarports().get(index);
        cartContents.setFinalPrice(cartContents.getFinalPrice() - removedItem.getPrice());
    }

    @Override
    public void recalculatePriceForItem(int index) throws MaterialLengthVariationNotFoundException {
        PurchaseCarport recalculatedItem = cartContents.getPurchaseCarports().get(index);
        int oldPrice = recalculatedItem.getPrice();
        int newPrice = priceCalculator.getPrice(recalculatedItem.getCarport(),recalculatedItem.getFrameDimensions());
        recalculatedItem.setPrice(newPrice);
        cartContents.setFinalPrice(cartContents.getFinalPrice() - oldPrice + newPrice);
    }

    @Override
    public void deleteCart() {
        session.removeAttribute(SESSION_IDENTIFIER);
    }

    @Override
    public Purchase getCartContents() {
        return cartContents;
    }

    @Override
    public int getPrice() {
        return cartContents.getFinalPrice();
    }

    @Override
    public int getNumberOfItems() {
        return cartContents.getPurchaseCarports().size();
    }
}
