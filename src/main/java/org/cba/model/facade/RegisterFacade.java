package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.domain.User;
import org.cba.domain.query.QUser;
import org.cba.model.Hasher;
import org.cba.model.exception.EmailTakenException;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by adam on 28/02/2017.
 */
public class RegisterFacade {

    public User registerUser(Map attributes) throws EmailTakenException {
        String email = (String) attributes.get("email");
        int exist = new QUser().email.equalTo(email).findCount();
        if (exist == 0) {
            User user = mapUser(attributes);
            Ebean.save(user);
            return user;
        } else {
            throw new EmailTakenException();
        }
    }

    private User mapUser(Map attributes) {
        Hasher hasher = new Hasher();
        String salt = hasher.generateSalt();
        String hashedPassword = hasher.hashPassword(((String) attributes.get("password")), salt);
        User user = new User();
        user.setName(((String) attributes.get("name")));
        user.setSurname(((String) attributes.get("surname")));
        user.setEmail(((String) attributes.get("email")));
        user.setType(1);
        user.setStatus(1);
        user.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        return user;
    }
}
