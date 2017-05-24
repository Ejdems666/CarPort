package org.cba.controller;

import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.AssemblyMaterial;
import org.cba.model.facade.AssemblyMaterialFacade;
import org.cba.parameter.ParameterFilter;
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
                AssemblyMaterialFacade facade = new AssemblyMaterialFacade();
                facade.add(parameters);
                alertSuccess("Assembly material added!");
            } catch (ParameterParserException e) {
                alertError("Wrong input!");
            }
        }
        renderTemplate();
    }

    private ParsedParameters getAssemblyMaterialParameters() throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addString("name").setRequired();
        parameterFilter.addInteger("price").setRequired();
        parameterFilter.addInteger("stock").setRequired();
        parameterFilter.addString("description").setRequired();
        return parameterFilter.parseParameters(request);
    }


    public void edit(Integer id) {
        AssemblyMaterial assemblyMaterial = AssemblyMaterial.find.byId(id);
        if (request.getMethod().equals("POST")) {
            try {
                ParsedParameters parameters = getAssemblyMaterialParameters();
                AssemblyMaterialFacade facade = new AssemblyMaterialFacade();
                facade.update(assemblyMaterial, parameters);
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
        TableBuilder tableBuilder = new TableBuilder("table");
        tableBuilder.addHeader("Assembly Materials", "Name,Price,Stock,Description,Edit link");
        for (AssemblyMaterial assemblyMaterial : assemblyMaterialList) {
            Row row = tableBuilder.createNewRow();
            row.addColumn(assemblyMaterial.getName());
            row.addColumn(String.valueOf(assemblyMaterial.getPrice()));
            row.addColumn(String.valueOf(assemblyMaterial.getStock()));
            row.addColumn(assemblyMaterial.getDescription());
            row.addColumn(getEditLink(assemblyMaterial));
        }
        request.setAttribute("table", tableBuilder);
        renderTemplate();
    }

    @NotNull
    private String getEditLink(AssemblyMaterial assemblyMaterial) {
        return "<a href='" + ROOT + "assembly-material/edit/" + assemblyMaterial.getId() + "'>" + assemblyMaterial.getId() + "</a>";
    }
}
