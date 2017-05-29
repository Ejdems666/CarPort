package org.cba.model.carport.formating.svg;

import org.cba.domain.Frame;
import org.cba.model.carport.calculation.*;

/**
 * Created by adam on 28/05/2017.
 */
public class BlueprintFactory {
    public BlueprintDrawing getSideFrameBlueprint(Frame frame, CarportSettings settings) {
        if (settings.isWithShed()) {
            ShedMaterialCalculator calculator = new FrameWithShedMaterialCalculator(
                    frame, settings.getFrameDimensions(), settings.getShedDimensions()
            );
            return new SideFrameWithShedBlueprint(
                    calculator,
                    settings.getFrameDimensions().length,
                    frame,
                    settings.getShedDimensions().length
            );
        } else {
            FrameMaterialCalculator calculator = new BareFrameMaterialCalculator(
                    frame, settings.getFrameDimensions()
            );
            return new SideBareFrameBlueprint(
                    calculator,
                    settings.getFrameDimensions().length,
                    frame
            );
        }
    }
}
