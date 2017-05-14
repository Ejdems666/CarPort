package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.cba.domain.MaterialLength;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by adam on 13/05/2017.
 */
public class FrameMaterialCalculatorTest {
    private Carport carport;
    private MaterialCalculator calculator;

    @BeforeMethod
    public void setUp() throws Exception {
        carport = Carport.find.byId(1);
        calculator = new FrameMaterialCalculator(carport.getFrame());
    }

    @Test
    public void testMaterialCalculationWithDefaultValues() throws Exception {
        int desiredLength = carport.getDefaultLength();
        int desiredWidth = carport.getDefaultWidth();
        List<ListedMaterial> calculatedMaterials = calculator.getRawMaterialsWithDesiredDimensions(
                desiredWidth,
                desiredLength
        );
        MaterialLength longerUpperPillarLV = calculatedMaterials.get(0).getMaterialLengthVariation();
        Assert.assertEquals(longerUpperPillarLV.getLength(), desiredLength);

        MaterialLength shorterUpperPillarLV = calculatedMaterials.get(1).getMaterialLengthVariation();
        Assert.assertEquals(shorterUpperPillarLV.getLength(), desiredWidth);

        MaterialLength lowerPillarLV = calculatedMaterials.get(2).getMaterialLengthVariation();
        Assert.assertEquals(lowerPillarLV.getLength(), desiredLength);

        ListedMaterial roofPlank = calculatedMaterials.get(3);
        MaterialLength roofPlankLV = roofPlank.getMaterialLengthVariation();
        Assert.assertEquals(roofPlankLV.getLength(), desiredWidth);
        Assert.assertEquals(roofPlank.getCount(), 15);

        ListedMaterial verticalPillar = calculatedMaterials.get(4);
        Assert.assertEquals(verticalPillar.getCount(), 10);
    }

    @Test
    public void testMaterialCalculationWithWrongLength() throws Exception {
        int desiredLength = 900;
        try {
            calculator.getRawMaterialsWithDesiredDimensions(
                    carport.getDefaultWidth(),
                    desiredLength
            );
        } catch (MaterialLengthVariationNotFoundException e) {
            Assert.assertEquals(
                    e.getMessage(),
                    "Material " + carport.getFrame().getUpperPillarMaterial().getName() +
                            " length variation of [" + desiredLength + " cm] wasn't found."
            );
            return;
        }
        Assert.fail("No length variation exception was thrown");
    }

    @Test
    public void testMaterialCalculationWithWrongWidth() throws Exception {
        int desiredWidth = 500;
        try {
            calculator.getRawMaterialsWithDesiredDimensions(
                    desiredWidth,
                    carport.getDefaultLength()
            );
        } catch (MaterialLengthVariationNotFoundException e) {
            Assert.assertEquals(
                    e.getMessage(),
                    "Material " + carport.getFrame().getRoofPlankMaterial().getName() +
                            " length variation of [" + desiredWidth + " cm] wasn't found."
            );
            return;
        }
        Assert.fail("No length variation exception was thrown");
    }
}