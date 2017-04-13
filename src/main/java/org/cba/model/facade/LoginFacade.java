package org.cba.model.facade;

import org.cba.domain.User;
import org.cba.domain.query.QUser;
import org.cba.model.Hasher;
import org.cba.model.exception.NonExistentEmailException;
import org.cba.model.exception.WrongPasswordException;

/**
 * Created by adam on 28/02/2017.
 */
public class LoginFacade {
    public User loginUser(String email, String password) throws WrongPasswordException, NonExistentEmailException {
        User user = new QUser().email.equalTo(email).findUnique();
        if (user != null) {
            Hasher hasher = new Hasher();
            String insertedPassword = hasher.hashPassword(password,user.getSalt());
            if (insertedPassword.equals(user.getPassword())) {
                return user;
            } else {
                throw new WrongPasswordException();
            }
        } else {
            throw new NonExistentEmailException();
        }
    }
}
