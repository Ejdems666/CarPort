package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.domain.RoofTile;
import org.cba.parameter.ParsedParameters;

/**
 * Created by István on 5/24/2017.
 */
public class RoofTileFacade {
    public RoofTile add(ParsedParameters parameters){
        RoofTile roofTile = new RoofTile();
        fillUpEntity(roofTile, parameters);
        Ebean.save(roofTile);
        return roofTile;
    }
    private void fillUpEntity(RoofTile roofTile, ParsedParameters parameters){
        roofTile.setName(parameters.getString("name"));
        roofTile.setWidth(parameters.getInteger("width"));
        roofTile.setWidthOverlap(parameters.getInteger("width overlap"));
        roofTile.setLength(parameters.getInteger("length"));
        roofTile.setLengthOverlap(parameters.getInteger("length overlap"));
        roofTile.setPrice(parameters.getInteger("price"));
        roofTile.setStock(parameters.getInteger("stock"));
        roofTile.setDescription(parameters.getString("description"));
    }
    public void update(RoofTile roofTile, ParsedParameters parameters){
        fillUpEntity(roofTile, parameters);
        Ebean.update(roofTile);
    }
}
