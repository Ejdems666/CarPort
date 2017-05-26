package org.cba.model.carport.calculation;

import org.cba.domain.RoofTile;

/**
 * Created by adam on 14/05/2017.
 */
public class FlatRoofTileCalculator implements RoofTileCalculator {
    @Override
    public int getNumberOfTiles(RoofTile roofTile, Dimensions carportDimensions) {
        int roofSurface = carportDimensions.width * carportDimensions.length;
        int tileSurface = (roofTile.getWidth() - roofTile.getWidthOverlap()) *
                (roofTile.getLength() - roofTile.getLengthOverlap());
        return roofSurface / tileSurface;
    }
}
