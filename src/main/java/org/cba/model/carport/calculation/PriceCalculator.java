package org.cba.model.carport.calculation;

import org.cba.domain.Carport;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;

/**
 * Created by adam on 20/04/2017.
 */
public class PriceCalculator {

    private int price = 0;

    public int getPrice(Carport carport, int desiredWidth, int desiredLength) {
        MaterialCalculator frameCalculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, desiredLength);
        try {
            addPriceForLVs(frameCalculator.getSideUpperPillars());
            addPriceForLVs(frameCalculator.getFrontAndBackUpperPillars());
            addPriceForLVs(frameCalculator.getLowerPillars());
            addPriceForLVs(frameCalculator.getRoofPlanksPillars());
            addPriceForLVs(frameCalculator.getVerticalPillars());
        } catch (MaterialLengthVariationNotFoundException e) {
            e.printStackTrace();
        }
        addPriceForRoofTiles(carport, desiredWidth, desiredLength);
        int profitMultiplier = carport.getProfitFromMaterials() / 100 + 1;
        return price * profitMultiplier;
    }

    private void addPriceForLVs(MaterialLengthAmountPair sideUpperPillar) {
        price += sideUpperPillar.getCount() * sideUpperPillar.getMaterialLengthVariation().getPrice();
    }

    private void addPriceForRoofTiles(Carport carport, int desiredWidth, int desiredLength) {
        RoofTileCalculator roofTileCalculator = new FlatRoofTileCalculator();
        int roofTiles = roofTileCalculator.getNumberOfTiles(carport.getRoofTile(), desiredWidth, desiredLength);
        price += carport.getRoofTile().getPrice() * roofTiles;
    }
}
