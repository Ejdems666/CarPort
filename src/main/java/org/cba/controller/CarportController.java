package org.cba.controller;

import org.cba.components.SelectBuilder;
import org.cba.domain.Carport;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;

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
        request.setAttribute("carport", carport);
        renderTemplate("carport/detail");
    }

    public void all() {
        List<Carport> carport = Carport.find.all();
        request.setAttribute("carports", carport);
        renderTemplate();
    }

    public void edit(Integer carportId) {
        Carport carport = Carport.find.byId(carportId);
        createSelectComponents(carport);
        request.setAttribute("carport", carport);
        renderTemplate();
    }

    private void createSelectComponents(Carport carport) {
        Material borderMaterial = carport.getFrame().getUpperPillarMaterial();
        SelectBuilder widthSelect = new SelectBuilder("Width", "width", carport.getDefaultWidth());
        SelectBuilder lengthSelect = new SelectBuilder("Length", "length",carport.getDefaultLength());
        for (MaterialLength materialLength : borderMaterial.getMaterialLengths()) {
            widthSelect.addOption(materialLength.getLength(), materialLength.getLength());
            lengthSelect.addOption(materialLength.getLength(), materialLength.getLength());
        }
        request.setAttribute("lengthSelect", lengthSelect);
        request.setAttribute("widthSelect", widthSelect);
    }

    public void unity() {
        renderTemplate("carport/unity", "webgl");
    }
}
