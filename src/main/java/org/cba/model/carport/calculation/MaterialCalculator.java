package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartRecord;

import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public interface MaterialCalculator {
    List<PartRecord> getAllPartRecords() throws MaterialLengthVariationNotFoundException;
}
