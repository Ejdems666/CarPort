package org.cba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lukas on 04/19/2017.
 */
public class StoreController extends BaseController {
    public StoreController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void main(){
        renderTemplate();
    }
}
