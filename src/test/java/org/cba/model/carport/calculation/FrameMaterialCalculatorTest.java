package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.cba.domain.MaterialLength;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by adam on 13/05/2017.
 */
public class FrameMaterialCalculatorTest {
    @Test
    public void testMaterialCalculationWithDefaultValues() throws Exception {
        Carport carport = Carport.find.byId(1);
        MaterialCalculator calculator = new FrameMaterialCalculator(carport.getFrame());
        int desiredLength = carport.getDefaultLength();
        int desiredWidth = carport.getDefaultWidth();
        List<ListedMaterial> calculatedMaterials = calculator.calculateMaterialsForDimensions(
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
}