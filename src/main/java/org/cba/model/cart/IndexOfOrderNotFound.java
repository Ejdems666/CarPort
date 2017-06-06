package org.cba.model.cart;

/**
 * Created by adam on 24/05/2017.
 */
public class IndexOfOrderNotFound extends Exception {
    public IndexOfOrderNotFound(int index) {
        super("Order number " + (index + 1) + " not found in cart");
    }
}
