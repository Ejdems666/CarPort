package org.cba.model.facade;
import org.cba.domain.Material;
import org.cba.parameter.ParsedParameters;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;


/**
 * Created by Maksymilian on 27/05/2017.
 */
public class MaterialFacadeTest {
    private MaterialFacade facade = new MaterialFacade();
    private ParsedParameters parameters;

    @BeforeMethod
    public void setUp() throws Exception {
        parameters = createMock(ParsedParameters.class);
        expect(parameters.getString("name")).andReturn("name");
        expect(parameters.getString("description")).andReturn("desc");
        expect(parameters.getInteger("height")).andReturn(30);
        expect(parameters.getInteger("width")).andReturn(15);
        replay(parameters);
    }

    @Test
    public void testAdd() throws Exception {
        int id = facade.add(parameters).getId();
        Material material = Material.find.byId(id);
        assertMockedData(material);

    }


        private void assertMockedData (Material material){
            Assert.assertEquals(material.getName(), "name");
            Assert.assertEquals(material.getDescription(), "desc");
            Assert.assertEquals(material.getHeight(), 30);
            Assert.assertEquals(material.getWidth(), 15);
        }

        @Test
        public void testUpdate () throws Exception {
            Material material = Material.find.byId(1);
            facade.update(material, parameters);
            assertMockedData(material);
        }

}