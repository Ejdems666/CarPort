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
        Assert.assertEquals(
                calculator.getNumberOfTiles(
                        carport.getRoofTile(),
                        carport.getDefaultWidth(),
                        carport.getDefaultLength()),
                128
        );
    }

}