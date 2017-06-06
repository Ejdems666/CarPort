package org.cba.controller;

import io.ebean.Ebean;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.Material;
import org.cba.model.facade.MaterialFacade;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.cba.Path.ROOT;

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
                MaterialFacade facade = new MaterialFacade();
                facade.add(parameters);
                alertSuccess("Assembly material added!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        renderTemplate();
    }

    private ParsedParameters getNormalParameters() throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addString("name").setRequired();
        parameterFilter.addInteger("height").setRequired();
        parameterFilter.addInteger("width").setRequired();
        parameterFilter.addString("description").setRequired();
        return parameterFilter.parseParameters(request);
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
            row.addColumn("<a href='" + ROOT + "material/edit/" + material.getId() + "'>" + material.getId() + "</a>");
        }
        request.setAttribute("table", tableBuilder);
        renderTemplate();


    }
}


