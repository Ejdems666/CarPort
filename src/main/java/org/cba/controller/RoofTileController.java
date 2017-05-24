package org.cba.controller;

import io.ebean.Ebean;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.RoofTile;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParameterParser;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Istvan on 5/21/2017.
 */
public class RoofTileController extends BaseController {
    public RoofTileController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void add() {
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getRoofTileParameters();
                RoofTile roofTile = new RoofTile();
                fillUpEntity(roofTile, parameters);
                Ebean.save(roofTile);
                alertSuccess("Roof tile added!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        renderTemplate();
    }

    private ParsedParameters getRoofTileParameters() throws ParameterParserException {
        ParameterFilter parameterSieve = createSieve();
        ParameterParser parameterParser = new ParameterParser();
        return parameterParser.parseParameters(request, parameterSieve);
    }

    @NotNull
    private ParameterFilter createSieve() {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addString("name").setRequired();
        parameterFilter.addInteger("width").setRequired();
        parameterFilter.addInteger("width overlap").setRequired();
        parameterFilter.addInteger("length").setRequired();
        parameterFilter.addInteger("length overlap").setRequired();
        parameterFilter.addInteger("price").setRequired();
        parameterFilter.addInteger("stock").setRequired();
        parameterFilter.addString("description").setRequired();
        return parameterFilter;
    }

    private void fillUpEntity(RoofTile roofTile, ParsedParameters parameters) {
        roofTile.setName(parameters.getString("name"));
        roofTile.setWidth(parameters.getInteger("width"));
        roofTile.setWidthOverlap(parameters.getInteger("width overlap"));
        roofTile.setLength(parameters.getInteger("length"));
        roofTile.setLengthOverlap(parameters.getInteger("length overlap"));
        roofTile.setPrice(parameters.getInteger("price"));
        roofTile.setStock(parameters.getInteger("stock"));
        roofTile.setDescription(parameters.getString("description"));
    }

    public void edit(Integer id) {
        RoofTile roofTile = RoofTile.find.byId(id);
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getRoofTileParameters();
                fillUpEntity(roofTile, parameters);
                Ebean.update(roofTile);
                alertSuccess("Roof Tile edited");
            } catch (ParameterParserException e) {
                alertError("Wrong input");
            }
        }
        request.setAttribute("tile", roofTile);
        renderTemplate();
    }

    public void index() {
        List<RoofTile> roofTileList = RoofTile.find.all();
        TableBuilder tableBuilder = new TableBuilder();
        tableBuilder.addHeader("Roof Tile", "Name, Width, Width overlap, Length, Length overlap, Price, Stock, Description, Edit link");
        for (RoofTile roofTile : roofTileList) {
            Row row = tableBuilder.createNewRow();
            row.addColumn(roofTile.getName());
            row.addColumn(String.valueOf(roofTile.getWidth()));
            row.addColumn(String.valueOf(roofTile.getWidthOverlap()));
            row.addColumn(String.valueOf(roofTile.getLength()));
            row.addColumn(String.valueOf(roofTile.getLengthOverlap()));
            row.addColumn(String.valueOf(roofTile.getPrice()));
            row.addColumn(String.valueOf(roofTile.getStock()));
            row.addColumn(roofTile.getDescription());
            row.addColumn("<a href='" + ROOT + "roof-tile/edit/" + roofTile.getId() + "'>" + roofTile.getId() + "</a>");
        }
        request.setAttribute("table", tableBuilder);
        renderTemplate();
    }

}
