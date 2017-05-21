package org.cba.controller;

import io.ebean.Ebean;
import org.cba.domain.AssemblyMaterial;
import org.cba.parameter.ParameterParser;
import org.cba.parameter.ParameterSieve;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by adam on 21/05/2017.
 */
public class AssemblyMaterialController extends BaseController {
    public AssemblyMaterialController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void add() {
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getAssemblyMaterialParameters();
                AssemblyMaterial assemblyMaterial = new AssemblyMaterial();
                fillUpEntity(assemblyMaterial, parameters);
                Ebean.save(assemblyMaterial);
                alertSuccess("Assembly material added!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        renderTemplate();
    }

    private ParsedParameters getAssemblyMaterialParameters() throws ParameterParserException {
        ParameterSieve parameterSieve = createSieve();
        ParameterParser parameterParser = new ParameterParser();
        return parameterParser.parseParameters(request, parameterSieve);
    }

    @NotNull
    private ParameterSieve createSieve() {
        ParameterSieve parameterSieve = new ParameterSieve();
        parameterSieve.addString("name").setRequired();
        parameterSieve.addInteger("price").setRequired();
        parameterSieve.addInteger("stock").setRequired();
        parameterSieve.addString("description").setRequired();
        return parameterSieve;
    }

    private void fillUpEntity(AssemblyMaterial assemblyMaterial, ParsedParameters parameters) {
        assemblyMaterial.setName(parameters.getString("name"));
        assemblyMaterial.setPrice(parameters.getInteger("price"));
        assemblyMaterial.setStock(parameters.getInteger("stock"));
        assemblyMaterial.setDescription(parameters.getString("description"));
    }

    public void edit(Integer id) {
        AssemblyMaterial assemblyMaterial = AssemblyMaterial.find.byId(id);
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getAssemblyMaterialParameters();
                fillUpEntity(assemblyMaterial, parameters);
                Ebean.update(assemblyMaterial);
                alertSuccess("Assembly material edited!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        request.setAttribute("material", assemblyMaterial);
        renderTemplate();
    }

    public void index() {
        List<AssemblyMaterial> assemblyMaterialList = AssemblyMaterial.find.all();
    }
}
