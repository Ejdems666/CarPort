package org.cba.controller;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.cba.domain.query.QUser;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created by Lukas on 05/16/2017.
 */
public class RemindPassController extends BaseController {
    public RemindPassController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void send() {
        if (request.getMethod().equals("POST")) {
            try {
                int exist = new QUser().email.equalTo(request.getParameter("email")).findCount();
                if(exist != 0){
                    generateKey();
                    //TODO: Insert key into DB.

                }
            } catch (Exception e){
                alertError("An user with given email does not exist.");
                }
            }
        renderTemplate("remind/send");
    }

    public String generateKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom randomKey = new SecureRandom();
            keyGen.init(randomKey);
            SecretKey secretKey = keyGen.generateKey();
            String finalKey = secretKey.toString();
            return finalKey;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public void sendMail(String email, String key) {
        String sendTo = email;
        String from = "admin@fog.dk";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
    }

}
