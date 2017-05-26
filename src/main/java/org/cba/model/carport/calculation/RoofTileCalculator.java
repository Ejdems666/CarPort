package org.cba.model.carport.calculation;

import org.cba.domain.RoofTile;

/**
 * Created by adam on 14/05/2017.
 */
public interface RoofTileCalculator {
    int getNumberOfTiles(RoofTile roofTile, Dimensions dimensions);
}
