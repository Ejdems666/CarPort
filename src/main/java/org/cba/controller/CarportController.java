package org.cba.controller;

import org.cba.domain.Carport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by adam on 15/04/2017.
 */
public class CarportController extends BaseController {
    public CarportController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void index(Integer carportId) {
        Carport carport = Carport.find.byId(carportId);
        request.setAttribute("carport",carport);
        renderTemplate("carport/detail");
    }

    public void all(){
        List<Carport> carport =  Carport.find.all();
        request.setAttribute("carports", carport);
        renderTemplate();
    }

    public void edit(Integer carportId) {
        Carport carport = Carport.find.byId(carportId);
        request.setAttribute("carport", carport);
        renderTemplate();
    }
}
