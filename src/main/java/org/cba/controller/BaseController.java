package org.cba.controller;

import hyggemvc.component.Alerts;
import hyggemvc.controller.Controller;
import org.cba.domain.User;
import org.cba.model.cart.Cart;
import org.cba.model.cart.SessionCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by adam on 26/02/2017.
 */
public abstract class BaseController extends Controller {
    public static final int ADMIN_TYPE = 2;
    protected final String ROOT = "/";
    protected final String ASSETS = ROOT + "assets/";
    protected final String CP_IMGS = ASSETS + "carport-images/";
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
        setTemplateVariablesAndConstants();
        super.renderTemplate(template);
    }

    private void setTemplateVariablesAndConstants() {
        request.setAttribute("root", ROOT);
        request.setAttribute("assets", ASSETS);
        request.setAttribute("cpImgs", CP_IMGS);
        request.setAttribute("cart", cart);
    }

    @Override
    protected void renderTemplate() {
        setTemplateVariablesAndConstants();
        super.renderTemplate();
    }

    @Override
    protected void renderTemplate(String template, String layout) {
        setTemplateVariablesAndConstants();
        super.renderTemplate(template, layout);
    }

    @Override
    protected void redirect(String url) {
        super.redirect(ROOT + url);
    }

    protected void redirect() {
        super.redirect(ROOT);
    }

    protected void redirectIfNotSignedIn() {
        if (!isLoggedIn()) {
            addAlert(Alerts.Type.ERROR, "First sign in mate.");
            redirect("sign/in");
        }
    }

    protected void redirectIfNotAdmin() {
        redirectIfNotSignedIn();
        if (!isAdmin()) {
            addAlert(Alerts.Type.ERROR, "You're not an admin mate.");
            redirect();
        }
    }

    private boolean isAdmin() {
        return loggedUser.getType() == ADMIN_TYPE;
    }
}
