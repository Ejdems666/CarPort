package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by adam on 13/05/2017.
 */
public class BareFrameMaterialCalculatorTest {
    private Carport carport;

    @BeforeMethod
    public void setUp() {
        carport = Carport.find.byId(1);
    }

    @Test
    public void testMaterialCalculationWithDefaultValues() throws Exception {
        Dimensions carportDimension = new Dimensions(carport.getDefaultLength(), carport.getDefaultWidth());
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), carportDimension);

        int sideUpperPillarLength = calculator.getSideUpperPillars().getLength();
        Assert.assertEquals(sideUpperPillarLength, carportDimension.length);

        int fbUpperPillarLength = calculator.getFrontAndBackUpperPillars().getLength();
        Assert.assertEquals(fbUpperPillarLength, carportDimension.width);

        int lowerPillarLength = calculator.getLowerPillars().getLength();
        Assert.assertEquals(lowerPillarLength, carportDimension.length);

        MaterialLengthRecord roofPlank = calculator.getRoofPlanks();
        Assert.assertEquals(roofPlank.getLength(), Integer.valueOf(carportDimension.width));
        Assert.assertEquals(roofPlank.getCount(), 15);

        MaterialLengthRecord verticalPillar = calculator.getVerticalPillars();
        Assert.assertEquals(verticalPillar.getCount(), 6);
    }

    @Test(expectedExceptions = {MaterialLengthVariationNotFoundException.class})
    public void testMaterialCalculationWithWrongLength() throws Exception {
        Dimensions carportDimension = new Dimensions(900, carport.getDefaultWidth());
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), carportDimension);
        calculator.getSideUpperPillars();
    }

    @Test
    public void testMaterialCalculationWithWrongWidth() throws Exception {
        Dimensions carportDimension = new Dimensions(carport.getDefaultLength(), 500);
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), carportDimension);
        calculator.getFrontAndBackUpperPillars();
    }
}