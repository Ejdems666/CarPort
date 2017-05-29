package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by adam on 14/05/2017.
 */
public class FlatRoofTileCalculatorTest {
    private Carport carport;
    private RoofTileCalculator calculator;

    @BeforeMethod
    public void setUp() throws Exception {
        carport = Carport.find.byId(1);
    }

    @Test
    public void testGetNumberOfTiles() {
        Dimensions frameDimensions = new Dimensions(carport.getFrameLength(), carport.getFrameWidth());
        calculator = new FlatRoofTileCalculator(carport.getRoofTile(),frameDimensions);
        Assert.assertEquals(calculator.getRoofTiles().getCount(), 128);
    }

}