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

    public PartsFormatter getPartsOfCarport(Dimensions carportDimensions) {
        addFrameMaterials(carportDimensions);
        addRoofTiles(carportDimensions);
        return formatter;
    }

    private void addRoofTiles(Dimensions carportDimensions) {
        RoofTileCalculator roofTileCalculator = new FlatRoofTileCalculator();
        RoofTile roofTile = carport.getRoofTile();
        int numberOfTiles = roofTileCalculator.getNumberOfTiles(
                roofTile,
                carportDimensions
        );
        formatter.addPartRecord(new RoofTileRecord(roofTile,numberOfTiles));
    }

    private void addFrameMaterials(Dimensions carportDimensions) {
        MaterialCalculator frameCalculator = new BareFrameMaterialCalculator(
                carport.getFrame(),
                carportDimensions
        );
        try {
            frameCalculator.addCalculatedMaterialsToFormatter(formatter);
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
    }
}
