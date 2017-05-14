package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by adam on 13/05/2017.
 */
public class BareFrameMaterialCalculatorTest {
    private Carport carport;

    @BeforeMethod
    public void setUp() throws Exception {
        carport = Carport.find.byId(1);
    }

    @Test
    public void testMaterialCalculationWithDefaultValues() throws Exception {
        int desiredLength = carport.getDefaultLength();
        int desiredWidth = carport.getDefaultWidth();
        MaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, desiredLength);

        MaterialLength sideUpperPillarLV = calculator.getSideUpperPillars().getMaterialLengthVariation();
        Assert.assertEquals(sideUpperPillarLV.getLength(), desiredLength);

        MaterialLength fbUpperPillarLV = calculator.getFrontAndBackUpperPillars().getMaterialLengthVariation();
        Assert.assertEquals(fbUpperPillarLV.getLength(), desiredWidth);

        MaterialLength lowerPillarLV = calculator.getLowerPillars().getMaterialLengthVariation();
        Assert.assertEquals(lowerPillarLV.getLength(), desiredLength);

        MaterialLengthAmountPair roofPlank = calculator.getRoofPlanksPillars();
        MaterialLength roofPlankLV = roofPlank.getMaterialLengthVariation();
        Assert.assertEquals(roofPlankLV.getLength(), desiredWidth);
        Assert.assertEquals(roofPlank.getCount(), 15);

        MaterialLengthAmountPair verticalPillar = calculator.getVerticalPillars();
        Assert.assertEquals(verticalPillar.getCount(), 10);
    }

    @Test(expectedExceptions = {MaterialLengthVariationNotFoundException.class})
    public void testMaterialCalculationWithWrongLength() throws Exception {
        int desiredLength = 900;
        MaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), carport.getDefaultWidth(), desiredLength);
        calculator.getSideUpperPillars();
    }

    @Test
    public void testMaterialCalculationWithWrongWidth() throws Exception {
        int desiredWidth = 500;
        MaterialCalculator calculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, carport.getDefaultLength());
        calculator.getFrontAndBackUpperPillars();
    }
}