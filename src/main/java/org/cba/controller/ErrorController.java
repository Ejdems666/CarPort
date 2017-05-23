package org.cba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 02/04/2017.
 */
public class ErrorController extends BaseController {

    public ErrorController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void notFound(Exception e) {
        renderTemplate();
    }


}
