package org.cba.controller;

import org.cba.model.exception.EmailTakenException;
import org.cba.model.exception.WrongPasswordException;
import org.cba.model.facade.ProfileFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 21/05/2017.
 */
public class ProfileController extends BaseController {
    public ProfileController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void email() {
        if (redirectIfNotSignedIn()) return;

        if (request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            ProfileFacade profileFacade = new ProfileFacade();
            try {
                profileFacade.changeEmail(loggedUser,email);
                alertSuccess("Email was updated");
            } catch (EmailTakenException e) {
                alertError(e.getMessage());
            }
        }
        renderTemplate();
    }

    public void password() {
        if (redirectIfNotSignedIn()) return;

        if (request.getMethod().equals("POST")) {
            String newPassword = request.getParameter("newPassword");
            String newPassword2 = request.getParameter("newPassword2");

            String oldPassword = request.getParameter("oldPassword");
            ProfileFacade profileFacade = new ProfileFacade();
            try {
                profileFacade.changePassword(oldPassword, loggedUser, newPassword, newPassword2);
                alertSuccess("Password was updated");
            } catch (WrongPasswordException e) {
                alertError(e.getMessage());
            }


        }
        renderTemplate();
    }
}
