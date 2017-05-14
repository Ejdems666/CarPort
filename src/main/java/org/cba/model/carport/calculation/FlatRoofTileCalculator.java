package org.cba.model.carport.calculation;

import org.cba.domain.RoofTile;

/**
 * Created by adam on 14/05/2017.
 */
public class FlatRoofTileCalculator implements RoofTileCalculator{
    @Override
    public int getNumberOfTiles(RoofTile roofTile, int frameWidth, int frameLength) {
        int roofSurface = frameWidth*frameLength;
        return roofSurface/roofTile.getSurface();
    }
}
