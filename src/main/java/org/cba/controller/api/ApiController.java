package org.cba.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hyggemvc.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by adam on 19/04/2017.
 */
public class ApiController extends Controller{
    public ApiController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    protected void processAndReturnJSON(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        String rawJson = null;
        try {
            rawJson = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        returnJSON(rawJson);
    }

    protected void returnJSON(String rawJson) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(rawJson);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
