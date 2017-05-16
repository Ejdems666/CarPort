package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartRecordsFormatter;

/**
 * Created by adam on 16/05/2017.
 */
public interface MaterialCalculator {
    void addCalculatedMaterialsToFormatter(PartRecordsFormatter formatter) throws MaterialLengthVariationNotFoundException;
}
