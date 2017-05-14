package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;

/**
 * Created by adam on 09/05/2017.
 */
public interface MaterialCalculator {
    MaterialLengthAmountPair getSideUpperPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthAmountPair getFrontAndBackUpperPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthAmountPair getLowerPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthAmountPair getRoofPlanksPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthAmountPair getVerticalPillars();
}
