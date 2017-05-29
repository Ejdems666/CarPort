package org.cba.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.cba.components.CarportSettingsForm;
import org.cba.controller.exception.NonExistentResourceException;
import org.cba.domain.Carport;
import org.cba.model.carport.calculation.CarportSettings;
import org.cba.model.carport.calculation.PriceCalculator;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 19/04/2017.
 */
public class CarportController extends ApiController {
    public CarportController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void getPrice(Integer carportId) {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        try {
            Carport carport = Carport.find.byId(carportId);
            if (carport == null) {
                throw new NonExistentResourceException("carport",carportId);
            }
            CarportSettings settings = CarportSettingsForm.getRequestedCarportSettings(request);
            PriceCalculator calculator = new PriceCalculator();
            int price = calculator.getPrice(carport,settings);
            objectNode.put("price", price);
        } catch (MaterialLengthVariationNotFoundException | ParameterParserException | NonExistentResourceException e) {
            objectNode.put("error", e.getMessage());
        }
        returnJSON(objectNode.toString());
    }
}
