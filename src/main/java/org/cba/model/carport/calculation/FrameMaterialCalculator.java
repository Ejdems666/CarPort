package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartRecord;

/**
 * Created by adam on 09/05/2017.
 */
public interface FrameMaterialCalculator extends MaterialCalculator{
    PartRecord getSideUpperPillars() throws MaterialLengthVariationNotFoundException;

    PartRecord getFrontAndBackUpperPillars() throws MaterialLengthVariationNotFoundException;

    PartRecord getLowerPillars() throws MaterialLengthVariationNotFoundException;

    PartRecord getRoofPlanks() throws MaterialLengthVariationNotFoundException;

    PartRecord getVerticalPillars();
}
