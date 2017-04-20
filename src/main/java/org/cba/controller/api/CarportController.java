package org.cba.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
        objectNode.put("price",123);
        returnJSON(objectNode.toString());
    }
}
