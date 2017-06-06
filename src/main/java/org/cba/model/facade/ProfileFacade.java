package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.domain.User;
import org.cba.model.Hasher;
import org.cba.model.exception.EmailTakenException;
import org.cba.model.exception.WrongPasswordException;

/**
 * Created by Rares on 26-May-17.
 */
public class ProfileFacade {

   public void changePassword(String oldPassword, User loggedUser, String newPassword, String newPassword2) throws WrongPasswordException {
       Hasher hasher = new Hasher();
       String oldPassHash = hasher.hashPassword(oldPassword, loggedUser.getSalt());

       if (oldPassHash.equals(loggedUser.getPassword())) {


           if (newPassword.equals(newPassword2)) {

               String newPassHash = hasher.hashPassword(newPassword, loggedUser.getSalt());
               loggedUser.setPassword(newPassHash);
               Ebean.update(loggedUser);

           } else {
               throw new WrongPasswordException("New passwords does not match");
           }


       } else {
           throw new WrongPasswordException("Old password does not match");
       }
   }

   public void changeEmail(User loggedUser, String email) throws EmailTakenException {
       int exist = User.find.where().email.equalTo(email).findCount();
       if (exist == 0) {
           loggedUser.setEmail(email);
           Ebean.update(loggedUser);
       } else {
           throw new EmailTakenException();
       }
   }
}
