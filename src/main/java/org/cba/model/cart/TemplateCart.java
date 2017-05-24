package org.cba.model.cart;

import org.cba.domain.Purchase;

/**
 * Created by adam on 24/05/2017.
 */
public interface TemplateCart {
    Purchase getCartContents();

    int getPrice();

    int getNumberOfItems();
}
