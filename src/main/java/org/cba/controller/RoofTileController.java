package org.cba.controller;

import io.ebean.Ebean;
import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.RoofTile;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        ParameterFilter filter = new ParameterFilter();
        filter.addString("name").setRequired();
        filter.addInteger("width").setRequired();
        filter.addInteger("width overlap").setRequired();
        filter.addInteger("length").setRequired();
        filter.addInteger("length overlap").setRequired();
        filter.addInteger("price").setRequired();
        filter.addInteger("stock").setRequired();
        filter.addString("description").setRequired();
        return filter.parseParameters(request);
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
        TableBuilder tableBuilder = new TableBuilder("table");
        tableBuilder.addHeader("Roof Tiles", "Name, Width, Width overlap, Length, Length overlap, Price, Stock, Description, Edit link");
        for (RoofTile roofTile : roofTileList) {
            Row row = tableBuilder.createNewRow();
            row.addColumn(roofTile.getName());
            row.addColumn(roofTile.getWidth());
            row.addColumn(roofTile.getWidthOverlap());
            row.addColumn(roofTile.getLength());
            row.addColumn(roofTile.getLengthOverlap());
            row.addColumn(roofTile.getPrice());
            row.addColumn(roofTile.getStock());
            row.addColumn(roofTile.getDescription());
            row.addColumnLink("roof-tile/edit/" + roofTile.getId(), Row.Icon.EDIT);
        }
        request.setAttribute("table", tableBuilder);
        renderTemplate();
    }

}
