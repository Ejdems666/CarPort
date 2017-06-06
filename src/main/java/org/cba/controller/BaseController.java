package org.cba.controller;

import hyggemvc.component.Alerts;
import hyggemvc.controller.Controller;
import org.cba.Path;
import org.cba.domain.User;
import org.cba.model.cart.Cart;
import org.cba.model.cart.SessionCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.cba.Path.ROOT;

/**
 * Created by adam on 26/02/2017.
 */
public abstract class BaseController extends Controller {
    public static final int ADMIN_TYPE = 2;
    protected User loggedUser;
    protected Cart cart;

    public BaseController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        loggedUser = getLoggedUser();
        cart = new SessionCart(request.getSession());
    }

    private User getLoggedUser() {
        HttpSession session = request.getSession();
        return ((User) session.getAttribute("user"));
    }

    protected boolean isLoggedIn() {
        return loggedUser != null;
    }

    @Override
    protected void renderTemplate(String template) {
        request.setAttribute("cart", cart);
        super.renderTemplate(template);
    }

    @Override
    protected void renderTemplate() {
        request.setAttribute("cart", cart);
        super.renderTemplate();
    }

    @Override
    protected void renderTemplate(String template, String layout) {
        request.setAttribute("cart", cart);
        super.renderTemplate(template, layout);
    }

    @Override
    protected void redirect(String url) {
        super.redirect(ROOT + url);
    }

    protected void redirect() {
        super.redirect(ROOT.toString());
    }

    protected void redirect(Path path, String url) {
        super.redirect(path + url);
    }

    protected boolean redirectIfNotSignedIn() {
        if (!isLoggedIn()) {
            addAlert(Alerts.Type.ERROR, "First sign in mate.");
            redirect("sign/in");
            return true;
        }
        return false;
    }

    protected boolean redirectIfNotAdmin() {
        if(redirectIfNotSignedIn()) {
            return true;
        }
        if (!isAdmin()) {
            addAlert(Alerts.Type.ERROR, "You're not an admin mate.");
            redirect();
            return true;
        }
        return false;
    }

    private boolean isAdmin() {
        return loggedUser.getType() == ADMIN_TYPE;
    }
}
