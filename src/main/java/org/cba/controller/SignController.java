package org.cba.controller;

import hyggemvc.component.Alerts;
import org.cba.domain.User;
import org.cba.model.exception.EmailTakenException;
import org.cba.model.exception.NonExistentEmailException;
import org.cba.model.exception.WrongPasswordException;
import org.cba.model.facade.LoginFacade;
import org.cba.model.facade.RegisterFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by adam on 26/02/2017.
 */
public class SignController extends BaseController {
    public SignController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void in() {
        if (request.getMethod().equals("POST")) {
            LoginFacade loginFacade = new LoginFacade();
            try {
                User user = loginFacade.loginUser(
                        request.getParameter("email"),
                        request.getParameter("password")
                );
                setLoginSession(user);
                redirect(ROOT);
                return;
            } catch (WrongPasswordException | NonExistentEmailException e) {
                alertError("This email and password pair doesn't exist.");
            }
        }
        renderTemplate("sign/in");
    }
    private void setLoginSession(User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30*60);
        alertSuccess("Successfully logged in");
    }

    public void up() {
        if (request.getMethod().equals("POST")) {
            RegisterFacade registerFacade = new RegisterFacade();
            try {
                User user = registerFacade.registerUser(getParameters());
                alertSuccess("Account was created");
                setLoginSession(user);
                redirect(ROOT);
                return;
            } catch (EmailTakenException e) {
                alertError(e.getMessage());
            }
        }
        renderTemplate("sign/up");
    }

    public void out() {
        if (isLoggedIn()) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.removeAttribute("cupcakes");
            alertSuccess("Successfully logged out.");
        } else {
            addAlert(Alerts.Type.WARNING, "Don't have to log out when you're not logged in.");
        }
        redirect(ROOT + "sign/in");
    }
}
