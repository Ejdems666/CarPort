package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartsFormatter;

/**
 * Created by adam on 16/05/2017.
 */
public interface MaterialCalculator {
    void addCalculatedMaterialsToFormatter(PartsFormatter formatter) throws MaterialLengthVariationNotFoundException;
}
