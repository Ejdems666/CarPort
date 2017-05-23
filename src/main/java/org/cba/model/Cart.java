package org.cba.model;

import org.cba.domain.Purchase;

import javax.servlet.http.HttpSession;

/**
 * Created by adam on 23/05/2017.
 */
public class Cart {
    private Purchase cartContents;

    public Cart(HttpSession session) {
        if (session.getAttribute("cart") == null) {
            cartContents = ((Purchase) session.getAttribute("cart"));
        } else {
            cartContents = new Purchase();
        }
    }

    public Purchase getCartContents() {
        return cartContents;
    }

    public void addItemToCart() {

    }
}
