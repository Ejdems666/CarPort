package org.cba.model.facade;

import org.cba.domain.AssemblyMaterial;
import org.cba.parameter.ParsedParameters;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.*;

/**
 * Created by adam on 23/05/2017.
 */
public class AssemblyMaterialFacadeTest {
    private AssemblyMaterialFacade facade = new AssemblyMaterialFacade();
    private AssemblyMaterial insertedMaterial;
    private ParsedParameters parameters;

    @BeforeMethod
    public void setUp() throws Exception {
        parameters = createMock(ParsedParameters.class);
    }

    @Test
    public void testAdd() throws Exception {
        expect(parameters.getString("name")).andReturn("name of screw");
        expect(parameters.getString("description")).andReturn("desc");
        expect(parameters.getInteger("price")).andReturn(1);
        expect(parameters.getInteger("stock")).andReturn(100);
        replay(parameters);
        int id = facade.add(parameters).getId();
        insertedMaterial = AssemblyMaterial.find.byId(id);
        Assert.assertEquals(insertedMaterial.getName(), "name of screw");
        Assert.assertEquals(insertedMaterial.getDescription(), "desc");
        Assert.assertEquals(insertedMaterial.getPrice(), 1);
        Assert.assertEquals(insertedMaterial.getStock(), 100);
    }

    @Test
    public void testUpdate() throws Exception {
        expect(parameters.getString("name")).andReturn("updated");
        expect(parameters.getString("description")).andReturn("desc updated");
        expect(parameters.getInteger("price")).andReturn(2);
        expect(parameters.getInteger("stock")).andReturn(200);
        replay(parameters);
        AssemblyMaterial updatedMaterial = AssemblyMaterial.find.byId(insertedMaterial.getId());
        facade.update(updatedMaterial,parameters);
        Assert.assertEquals(updatedMaterial.getName(), "updated");
        Assert.assertEquals(updatedMaterial.getDescription(), "desc updated");
        Assert.assertEquals(updatedMaterial.getPrice(), 2);
        Assert.assertEquals(updatedMaterial.getStock(), 200);
    }

}