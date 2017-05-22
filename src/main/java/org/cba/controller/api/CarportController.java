package org.cba.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.cba.domain.Carport;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.model.carport.calculation.PriceCalculator;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.parameter.ParameterParser;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;
import org.jetbrains.annotations.NotNull;

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
            ParsedParameters parameters = getRequestParameters();
            Carport carport = Carport.find.byId(carportId);
            PriceCalculator calculator = new PriceCalculator();
            Dimensions requestedCarportDimensions = new Dimensions(
                    parameters.getInteger("width"),
                    parameters.getInteger("length")
            );
            int price = calculator.getPrice(carport,requestedCarportDimensions);
            objectNode.put("price", price);
        } catch (ParameterParserException | MaterialLengthVariationNotFoundException e) {
            objectNode.put("error", e.getMessage());
        }
        returnJSON(objectNode.toString());
    }

    @NotNull
    private ParsedParameters getRequestParameters() throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addInteger("width").setRequired();
        parameterFilter.addInteger("length").setRequired();
        ParameterParser parameterParser = new ParameterParser();
        return parameterParser.parseParameters(request, parameterFilter);
    }
}
