package org.cba.model.facade;
import io.ebean.Ebean;
import org.cba.domain.Material;
import org.cba.parameter.ParsedParameters;

/**
 * Created by Maksymilian on 26/05/2017.
 */
public class MaterialFacade {
    public Material add(ParsedParameters parameters) {
        Material material = new Material();
        fillUpEntity(material, parameters);
        Ebean.save(material);
        return material;
    }

    private void fillUpEntity(Material material, ParsedParameters parameters) {
        material.setName(parameters.getString("name"));
        material.setWidth(parameters.getInteger("width"));
        material.setHeight(parameters.getInteger("height"));
        material.setDescription(parameters.getString("description"));
    }

    public void update(Material material, ParsedParameters parameters) {
        fillUpEntity(material, parameters);
        Ebean.update(material);
    }
}



