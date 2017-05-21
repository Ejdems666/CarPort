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
        int desiredLength = carport.getDefaultLength();
        int desiredWidth = carport.getDefaultWidth();
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, desiredLength);

        int sideUpperPillarLength = calculator.getSideUpperPillars().getLength();
        Assert.assertEquals(sideUpperPillarLength, desiredLength);

        int fbUpperPillarLength = calculator.getFrontAndBackUpperPillars().getLength();
        Assert.assertEquals(fbUpperPillarLength, desiredWidth);

        int lowerPillarLength = calculator.getLowerPillars().getLength();
        Assert.assertEquals(lowerPillarLength, desiredLength);

        MaterialLengthRecord roofPlank = calculator.getRoofPlanks();
        Assert.assertEquals(roofPlank.getLength(), Integer.valueOf(desiredWidth));
        Assert.assertEquals(roofPlank.getCount(), 15);

        MaterialLengthRecord verticalPillar = calculator.getVerticalPillars();
        Assert.assertEquals(verticalPillar.getCount(), 6);
    }

    @Test(expectedExceptions = {MaterialLengthVariationNotFoundException.class})
    public void testMaterialCalculationWithWrongLength() throws Exception {
        int desiredLength = 900;
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), carport.getDefaultWidth(), desiredLength);
        calculator.getSideUpperPillars();
    }

    @Test
    public void testMaterialCalculationWithWrongWidth() throws Exception {
        int desiredWidth = 500;
        FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, carport.getDefaultLength());
        calculator.getFrontAndBackUpperPillars();
    }
}