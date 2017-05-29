package org.cba.model.carport.calculation;

import org.cba.model.carport.calculation.Dimensions;

/**
 * Created by adam on 28/05/2017.
 */
public interface CarportSettings {
    public Dimensions getFrameDimensions();

    public boolean isWithShed();

    public Dimensions getShedDimensions();
}
