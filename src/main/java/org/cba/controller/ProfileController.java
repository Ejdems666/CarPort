package org.cba.controller;

import io.ebean.Ebean;
import org.cba.domain.query.QUser;
import org.cba.model.Hasher;
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
        if (!isLoggedIn()) {
            redirectIfNotSignedIn();
            return;
        }
        if (request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            int exist = new QUser().email.equalTo(email).findCount();
            if (exist == 0) {

                loggedUser.setEmail(email);
                Ebean.update(loggedUser);
                alertSuccess("Email was updated");
            }
            else
                alertError("Email already exist");
        }
        renderTemplate();
    }

    public void password() {
        if (!isLoggedIn()) {
            redirectIfNotSignedIn();
            return;
        }
        if (request.getMethod().equals("POST")) {
            String newPassword = request.getParameter("newPassword");
            String newPassword2 = request.getParameter("newPassword2");

            String oldPassword = request.getParameter("oldPassword");
            ProfileFacade profileFacade= new ProfileFacade();
            try {
                profileFacade.changePassword(oldPassword,loggedUser,newPassword, newPassword2);
                alertSuccess("Password was updated");
            } catch (WrongPasswordException e) {
                alertError(e.getMessage());
            }


        }
        renderTemplate();
    }
}
