package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;
import org.cba.model.carport.formating.PartsFormatter;

import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public interface MaterialCalculator {
    void addCalculatedMaterialsToFormatter(PartsFormatter formatter) throws MaterialLengthVariationNotFoundException;
    List<MaterialLengthRecord> getAllMaterialRecords() throws MaterialLengthVariationNotFoundException;
}
