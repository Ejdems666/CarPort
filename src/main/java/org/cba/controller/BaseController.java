package org.cba.controller;

import hyggemvc.component.Alerts;
import hyggemvc.controller.Controller;
import org.cba.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 26/02/2017.
 */
public abstract class BaseController extends Controller {
    public static final int ADMIN_TYPE = 2;
    protected final String ROOT = "/";
    protected final String ASSETS = ROOT + "assets/";
    protected User loggedUser = null;

    public BaseController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        loggedUser = getLoggedUser();
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
        request.setAttribute("root", ROOT);
        request.setAttribute("assets", ASSETS);
        super.renderTemplate(template);
    }

    @Override
    protected void renderTemplate() {
        request.setAttribute("root", ROOT);
        request.setAttribute("assets", ASSETS);
        super.renderTemplate();
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

    protected Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        Enumeration names = request.getParameterNames();
        String key;
        String value;
        Integer intValue;
        while (names.hasMoreElements()) {
            key = ((String) names.nextElement());
            value = request.getParameter(key);
            try {
                intValue = Integer.parseInt(value);
                parameters.put(key, intValue);
            } catch (NumberFormatException e) {
                parameters.put(key, value);
            }
        }
        return parameters;
    }
}
