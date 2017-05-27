package org.cba.model.carport.calculation;

import org.cba.domain.RoofTile;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartRecord;
import org.cba.model.carport.formating.RoofTileRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 14/05/2017.
 */
public class FlatRoofTileCalculator implements RoofTileCalculator {
    private RoofTile roofTile;
    private Dimensions frameDimensions;

    public FlatRoofTileCalculator(RoofTile roofTile, Dimensions frameDimensions) {
        this.roofTile = roofTile;
        this.frameDimensions = frameDimensions;
    }

    @Override
    public PartRecord getRoofTiles() {
        int roofSurface = frameDimensions.width * frameDimensions.length;
        int tileSurface = (roofTile.getWidth() - roofTile.getWidthOverlap()) *
                (roofTile.getLength() - roofTile.getLengthOverlap());
        int numbedOfTiles = roofSurface / tileSurface;
        return new RoofTileRecord(roofTile,numbedOfTiles);
    }

    @Override
    public List<PartRecord> getAllPartRecords() throws MaterialLengthVariationNotFoundException {
        List<PartRecord> partRecords = new ArrayList<>();
        partRecords.add(getRoofTiles());
        return partRecords;
    }
}
