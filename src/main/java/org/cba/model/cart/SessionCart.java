package org.cba.model.cart;

import io.ebean.Ebean;
import org.cba.domain.Carport;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.model.carport.calculation.PriceCalculator;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.jetbrains.annotations.NotNull;

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
        cartContents.addPurchaseCarport(new PurchaseCarport(carport, frameDimensions, newItemPrice, cartContents));
        cartContents.setFinalPrice(newItemPrice + cartContents.getFinalPrice());
    }

    @Override
    public void removeItem(int index) throws IndexOfOrderNotFound {
        PurchaseCarport removedItem = getItem(index);
        cartContents.setFinalPrice(cartContents.getFinalPrice() - removedItem.getPrice());
        cartContents.getPurchaseCarports().remove(index);
    }

    @NotNull
    @Override
    public PurchaseCarport getItem(int index) throws IndexOfOrderNotFound {
        PurchaseCarport removedItem;
        try {
            removedItem = cartContents.getPurchaseCarports().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOfOrderNotFound(index);
        }
        return removedItem;
    }

    @Override
    public void recalculatePriceForItem(int index) throws MaterialLengthVariationNotFoundException, IndexOfOrderNotFound {
        PurchaseCarport recalculatedItem = getItem(index);
        int oldPrice = recalculatedItem.getPrice();
        int newPrice = priceCalculator.getPrice(recalculatedItem.getCarport(), recalculatedItem.getFrameDimensions());
        recalculatedItem.setPrice(newPrice);
        cartContents.setFinalPrice(cartContents.getFinalPrice() - oldPrice + newPrice);
    }

    @Override
    public void saveInDatabaseAndEmptyCart() {
        Ebean.save(cartContents);
        Ebean.saveAll(cartContents.getPurchaseCarports());
        session.removeAttribute(SESSION_IDENTIFIER);
        cartContents = new Purchase();
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
