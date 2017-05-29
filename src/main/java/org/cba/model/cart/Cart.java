package org.cba.model.cart;

import org.cba.domain.Carport;
import org.cba.model.carport.calculation.CarportSettings;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;

/**
 * Created by adam on 24/05/2017.
 */
public interface Cart extends TemplateCart {
    void addItem(Carport carport, CarportSettings settingsw) throws MaterialLengthVariationNotFoundException;

    public void removeItem(int index) throws IndexOfOrderNotFound;

    /**
     * Must be called once frameDimensions of certain PurchaseCarport are changed
     */
    public void recalculatePriceForItem(int index, CarportSettings carportSettings) throws MaterialLengthVariationNotFoundException, IndexOfOrderNotFound;

    public void saveInDatabaseAndEmptyCart();

    public PurchaseCarport getItem(int index) throws IndexOfOrderNotFound;
}
