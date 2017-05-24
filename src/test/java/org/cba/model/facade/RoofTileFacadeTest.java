package org.cba.model.facade;

import org.cba.domain.RoofTile;
import org.cba.parameter.ParsedParameters;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * Created by AR on 5/24/2017.
 */
public class RoofTileFacadeTest {
    private RoofTileFacade facade = new RoofTileFacade();
    private RoofTile insertedTile;
    private ParsedParameters parameters;

    @BeforeMethod
    public void setUp() throws Exception{
        parameters = createMock(ParsedParameters.class);
    }

    @Test
    public void testAdd() throws Exception{
        expect(parameters.getString("name")).andReturn("test tile name");
        expect(parameters.getInteger("width")).andReturn(10);
        expect(parameters.getInteger("width overlap")).andReturn(15);
        expect(parameters.getInteger("length")).andReturn(1);
        expect(parameters.getInteger("length overlap")).andReturn(30);
        expect(parameters.getInteger("price")).andReturn(200);
        expect(parameters.getInteger("stock")).andReturn(40);
        expect(parameters.getString("description")).andReturn("test desc");
        replay(parameters);
        int id = facade.add(parameters).getId();
        insertedTile = RoofTile.find.byId(id);
        Assert.assertEquals(insertedTile.getName(), "test tile name");
        Assert.assertEquals(insertedTile.getWidth(), 10);
        Assert.assertEquals(insertedTile.getWidthOverlap(), 15);
        Assert.assertEquals(insertedTile.getLength(), 1);
        Assert.assertEquals(insertedTile.getLengthOverlap(), 30);
        Assert.assertEquals(insertedTile.getPrice(), 200);
        Assert.assertEquals(insertedTile.getStock(),40);
        Assert.assertEquals(insertedTile.getDescription(), "test desc");
    }

    @Test
    public void testUpdate()throws Exception{
        expect(parameters.getString("name")).andReturn("updated name");
        expect(parameters.getInteger("width")).andReturn(20);
        expect(parameters.getInteger("width overlap")).andReturn(25);
        expect(parameters.getInteger("length")).andReturn(2);
        expect(parameters.getInteger("length overlap")).andReturn(11);
        expect(parameters.getInteger("price")).andReturn(1200);
        expect(parameters.getInteger("stock")).andReturn(51);
        expect(parameters.getString("description")).andReturn("updated test desc");
        replay(parameters);
        RoofTile updatedTile = RoofTile.find.byId(insertedTile.getId());
        facade.update(updatedTile, parameters);
        Assert.assertEquals(updatedTile.getName(), "updated name");
        Assert.assertEquals(updatedTile.getWidth(), 20);
        Assert.assertEquals(updatedTile.getWidthOverlap(), 25);
        Assert.assertEquals(updatedTile.getLength(), 2);
        Assert.assertEquals(updatedTile.getLengthOverlap(), 11);
        Assert.assertEquals(updatedTile.getPrice(), 1200);
        Assert.assertEquals(updatedTile.getStock(), 51);
        Assert.assertEquals(updatedTile.getDescription(),"updated test dec");
    }
}
