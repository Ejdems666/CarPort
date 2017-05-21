package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.MaterialLengthRecord;

/**
 * Created by adam on 09/05/2017.
 */
public interface FrameMaterialCalculator extends MaterialCalculator{
    MaterialLengthRecord getSideUpperPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthRecord getFrontAndBackUpperPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthRecord getLowerPillars() throws MaterialLengthVariationNotFoundException;

    MaterialLengthRecord getRoofPlanks() throws MaterialLengthVariationNotFoundException;

    MaterialLengthRecord getVerticalPillars();
}
