package org.cba.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.cba.domain.Carport;
import org.cba.model.facade.PriceCalculator;
import org.cba.parameter.ParameterParser;
import org.cba.parameter.ParameterSieve;
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
        ParameterSieve parameterSieve = createPriceParametersSieve();
        ParameterParser parameterParser = new ParameterParser();
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        try {
            ParsedParameters parameters = parameterParser.parseParameters(request, parameterSieve);
            Carport carport = Carport.find.byId(carportId);
            PriceCalculator calculator = new PriceCalculator();
            int price = calculator.getPrice(carport, parameters.getInteger("width"), parameters.getInteger("length"));
            objectNode.put("price", price);
        } catch (ParameterParserException e) {
            objectNode.put("error", e.getMessage());
        }
        returnJSON(objectNode.toString());
    }

    @NotNull
    private ParameterSieve createPriceParametersSieve() {
        ParameterSieve parameterSieve = new ParameterSieve();
        parameterSieve.addInteger("width").setRequired();
        parameterSieve.addInteger("length").setRequired();
        return parameterSieve;
    }
}
