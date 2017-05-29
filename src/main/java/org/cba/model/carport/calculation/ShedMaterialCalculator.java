package org.cba.model.carport.calculation;

import org.cba.model.carport.formating.PartRecord;

/**
 * Created by adam on 28/05/2017.
 */
public interface ShedMaterialCalculator extends FrameMaterialCalculator{
    int SHED_RESERVE = 30;
    int SHED_PLANK_OVERLAP = 3;

    PartRecord getShedPlanks();

    // For easier use in SVG generator
    PartRecord getSideShedPlanks();
    PartRecord getSideShedVerticalPillars();
    PartRecord getRegularVerticalPillars();
}
