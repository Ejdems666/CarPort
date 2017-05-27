package org.cba.components;

import org.cba.domain.Carport;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.Dimensions;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adam on 24/05/2017.
 */
public class CarportEditForm {
    public static void createAndPassSelectComponents(HttpServletRequest request, Carport carport, Dimensions selectedFrameDimensions) {
        Material borderMaterial = carport.getFrame().getUpperPillarMaterial();
        SelectBuilder widthSelect = new SelectBuilder("Width", "frameWidth", selectedFrameDimensions.width);
        SelectBuilder lengthSelect = new SelectBuilder("Length", "frameLength",selectedFrameDimensions.length);
        for (MaterialLength materialLength : borderMaterial.getMaterialLengths()) {
            widthSelect.addOption(materialLength.getLength(), materialLength.getLength());
            lengthSelect.addOption(materialLength.getLength(), materialLength.getLength());
        }
        request.setAttribute("lengthSelect", lengthSelect);
        request.setAttribute("widthSelect", widthSelect);
    }
}
