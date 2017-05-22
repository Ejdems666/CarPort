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
        calculator = new FlatRoofTileCalculator();
    }

    @Test
    public void testGetNumberOfTiles() {
        Dimensions carportDimension = new Dimensions(carport.getDefaultLength(), carport.getDefaultWidth());
        Assert.assertEquals(calculator.getNumberOfTiles(carport.getRoofTile(), carportDimension), 128);
    }

}