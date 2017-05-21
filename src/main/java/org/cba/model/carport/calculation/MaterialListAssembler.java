package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.cba.domain.RoofTile;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.PartsFormatter;
import org.cba.model.carport.formating.RoofTileRecord;

/**
 * Created by adam on 14/05/2017.
 */
public class MaterialListAssembler {
    private Carport carport;
    private PartsFormatter formatter;

    public MaterialListAssembler(Carport carport, PartsFormatter formatter) {
        this.carport = carport;
        this.formatter = formatter;
    }

    public PartsFormatter getPartsOfCarport(int desiredWidth, int desiredLength) {
        addFrameMaterials(desiredWidth, desiredLength);
        addRoofTiles(desiredWidth, desiredLength);
        return formatter;
    }

    private void addRoofTiles(int desiredWidth, int desiredLength) {
        RoofTileCalculator roofTileCalculator = new FlatRoofTileCalculator();
        RoofTile roofTile = carport.getRoofTile();
        int numberOfTiles = roofTileCalculator.getNumberOfTiles(
                roofTile,
                desiredWidth,
                desiredLength
        );
        formatter.addPartRecord(new RoofTileRecord(roofTile,numberOfTiles));
    }

    private void addFrameMaterials(int desiredWidth, int desiredLength) {
        MaterialCalculator frameCalculator = new BareFrameMaterialCalculator(
                carport.getFrame(),
                desiredWidth,
                desiredLength
        );
        try {
            frameCalculator.addCalculatedMaterialsToFormatter(formatter);
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
    }
}
