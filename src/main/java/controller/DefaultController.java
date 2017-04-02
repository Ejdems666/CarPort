package controller;

import hyggemvc.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 02/04/2017.
 */
public class DefaultController extends Controller {
    public DefaultController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void index() {
        renderTemplate() ;
    }
}
