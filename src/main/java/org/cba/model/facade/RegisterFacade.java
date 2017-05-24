package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.domain.User;
import org.cba.domain.query.QUser;
import org.cba.model.Hasher;
import org.cba.model.exception.EmailTakenException;
import org.cba.parameter.ParsedParameters;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by adam on 28/02/2017.
 */
public class RegisterFacade {

    public User registerUser(ParsedParameters parameters) throws EmailTakenException {
        String email = parameters.getString("email");
        int exist = new QUser().email.equalTo(email).findCount();
        if (exist == 0) {
            User user = mapUser(parameters);
            Ebean.save(user);
            return user;
        } else {
            throw new EmailTakenException();
        }
    }

    private User mapUser(ParsedParameters parameters) {
        Hasher hasher = new Hasher();
        String salt = hasher.generateSalt();
        String hashedPassword = hasher.hashPassword(parameters.getString("password"), salt);
        User user = new User();
        user.setName(parameters.getString("name"));
        user.setSurname(parameters.getString("surname"));
        user.setEmail(parameters.getString("email"));
        user.setType(1);
        user.setStatus(1);
        user.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        return user;
    }
}
