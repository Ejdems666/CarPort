package org.cba.model.carport.calculation;

/**
 * Created by adam on 28/05/2017.
 */
public class CarportSettingsHolder implements CarportSettings{
    private Dimensions frameDimensions;
    private Dimensions shedDimensions;
    private boolean withShed;

    public CarportSettingsHolder(Dimensions frameDimensions, Dimensions shedDimensions, boolean withShed) {
        this.frameDimensions = frameDimensions;
        this.shedDimensions = shedDimensions;
        this.withShed = withShed;
    }

    public Dimensions getFrameDimensions() {
        return frameDimensions;
    }

    public Dimensions getShedDimensions() {
        return shedDimensions;
    }

    public boolean isWithShed() {
        return withShed;
    }
}
