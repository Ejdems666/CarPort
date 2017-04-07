package controller;

import hyggedb.HyggeDb;
import hyggemvc.component.Alerts;
import hyggemvc.controller.Controller;
import model.Connector;
import model.entity.User;

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
    private HyggeDb db = null;
    public static final int ADMIN_TYPE = 2;
    protected final String ROOT = "/";
    protected final String ASSETS = ROOT+"assets/";
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

    protected HyggeDb getDatabase() {
        if (db == null) {
            return db = new HyggeDb(new Connector());
        } else {
            return db;
        }
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

    protected void redirectIfNotSignedIn() {
        if (!isLoggedIn()) {
            addAlert(Alerts.Type.ERROR, "First sign in mate.");
            redirect(ROOT + "sign/in");
        }
    }

    protected void redirectIfNotAdmin() {
        redirectIfNotSignedIn();
        if (!isAdmin()) {
            addAlert(Alerts.Type.ERROR, "You're not an admin mate.");
            redirect(ROOT);
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
