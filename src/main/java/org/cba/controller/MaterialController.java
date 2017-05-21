package org.cba.controller;

import io.ebean.Ebean;
import org.cba.controller.BaseController;
import org.cba.domain.Material;
import org.cba.model.carport.formating.table.Row;
import org.cba.model.carport.formating.table.TableBuilder;
import org.cba.parameter.ParameterParser;
import org.cba.parameter.ParameterSieve;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Maksymilian on 21/05/2017.
 */
public class MaterialController extends BaseController {
    public MaterialController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void add() {
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getNormalParameters();
                Material material = new Material();
                fillUpEntity(material, parameters);
                Ebean.save(material);
                alertSuccess("Assembly material added!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        renderTemplate();
    }

    private ParsedParameters getNormalParameters() throws ParameterParserException {
        ParameterSieve parameterSieve = createSieve();
        ParameterParser parameterParser = new ParameterParser();
        return parameterParser.parseParameters(request, parameterSieve);
    }

    @NotNull
    private ParameterSieve createSieve() {
        ParameterSieve parameterSieve = new ParameterSieve();
        parameterSieve.addString("name").setRequired();
        parameterSieve.addInteger("height").setRequired();
        parameterSieve.addInteger("width").setRequired();
        parameterSieve.addString("description").setRequired();
        return parameterSieve;
    }

    private void fillUpEntity(Material material, ParsedParameters parameters) {
        material.setName(parameters.getString("name"));
        material.setWidth(parameters.getInteger("width"));
        material.setHeight(parameters.getInteger("height"));
        material.setDescription(parameters.getString("description"));
    }

    public void edit(Integer id) {
        Material material = Material.find.byId(id);
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getNormalParameters();
                fillUpEntity(material, parameters);
                Ebean.update(material);
                alertSuccess("Assembly material edited!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        request.setAttribute("material", material);
        renderTemplate();
    }

    public void index() {
        List<Material> materialList = Material.find.all();
        TableBuilder tableBuilder = new TableBuilder();
        tableBuilder.addHeader("Materials","Name,Width,Height,Description");
        for (Material material : materialList) {
            Row row = tableBuilder.createNewRow();
            row.addColumn(material.getName());
            row.addColumn(String.valueOf(material.getWidth()));
            row.addColumn(String.valueOf(material.getHeight()));
            row.addColumn(material.getDescription());
            // add Edit Link if needed.

        }
        request.setAttribute("table", tableBuilder);
        renderTemplate();


    }
}


