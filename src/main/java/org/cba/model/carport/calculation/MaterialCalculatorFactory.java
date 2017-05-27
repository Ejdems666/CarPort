package org.cba.model.carport.calculation;

import org.cba.domain.Carport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 25/05/2017.
 */
public class MaterialCalculatorFactory {
    public List<MaterialCalculator> getMaterialCalculators(Carport carport, Dimensions frameDimensions) {
        List<MaterialCalculator> materialCalculators = new ArrayList<>();
        materialCalculators.add(new FlatRoofTileCalculator(carport.getRoofTile(),frameDimensions));
        materialCalculators.add(new BareFrameMaterialCalculator(carport.getFrame(),frameDimensions));
        return materialCalculators;
    }
}
