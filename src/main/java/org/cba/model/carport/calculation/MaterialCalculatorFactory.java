package org.cba.model.carport.calculation;

import org.cba.domain.Carport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 25/05/2017.
 */
public class MaterialCalculatorFactory {
    public List<MaterialCalculator> getMaterialCalculators(Carport carport, CarportSettings settings) {
        List<MaterialCalculator> materialCalculators = new ArrayList<>();
        materialCalculators.add(new FlatRoofTileCalculator(carport.getRoofTile(), settings.getFrameDimensions()));
        if (settings.isWithShed()) {
            materialCalculators.add(new FrameWithShedMaterialCalculator(carport.getFrame(), settings.getFrameDimensions(), settings.getShedDimensions()));
        } else {
            materialCalculators.add(new BareFrameMaterialCalculator(carport.getFrame(), settings.getFrameDimensions()));
        }
        return materialCalculators;
    }
}
