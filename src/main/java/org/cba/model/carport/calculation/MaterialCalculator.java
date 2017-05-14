package org.cba.model.carport.calculation;

import java.util.List;

/**
 * Created by adam on 09/05/2017.
 */
public interface MaterialCalculator {
    List<ListedMaterial> getRawMaterialsWithDesiredDimensions(int width, int length) throws MaterialLengthVariationNotFoundException;
}
