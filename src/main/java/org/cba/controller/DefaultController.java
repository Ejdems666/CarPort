package org.cba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 02/04/2017.
 */
public class DefaultController extends BaseController {

    public DefaultController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void index() {
        renderTemplate() ;
    }

    public void test() {

    }
}
