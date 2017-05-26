package org.cba.model.facade;

import io.ebean.Ebean;
import org.cba.domain.AssemblyMaterial;
import org.cba.parameter.ParsedParameters;

/**
 * Created by adam on 23/05/2017.
 */
public class AssemblyMaterialFacade {
    public AssemblyMaterial add(ParsedParameters parameters) {
        AssemblyMaterial assemblyMaterial = new AssemblyMaterial();
        fillUpEntity(assemblyMaterial, parameters);
        Ebean.save(assemblyMaterial);
        return assemblyMaterial;
    }

    private void fillUpEntity(AssemblyMaterial assemblyMaterial, ParsedParameters parameters) {
        assemblyMaterial.setName(parameters.getString("name"));
        assemblyMaterial.setPrice(parameters.getInteger("price"));
        assemblyMaterial.setStock(parameters.getInteger("stock"));
        assemblyMaterial.setDescription(parameters.getString("description"));
    }

    public void update(AssemblyMaterial assemblyMaterial,ParsedParameters parameters) {
        fillUpEntity(assemblyMaterial, parameters);
        Ebean.update(assemblyMaterial);
    }
}
