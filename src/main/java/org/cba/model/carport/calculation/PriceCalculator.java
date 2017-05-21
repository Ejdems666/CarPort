package org.cba.model.carport.calculation;

import org.cba.domain.AssemblyMaterial;
import org.cba.domain.Carport;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.AssemblyMaterialRecords;
import org.cba.model.carport.formating.MaterialLengthRecord;

import java.util.Map;

/**
 * Created by adam on 20/04/2017.
 */
public class PriceCalculator {

    private int price = 0;
    private AssemblyMaterialRecords assemblyMaterialRecords = new AssemblyMaterialRecords();

    public int getPrice(Carport carport, int desiredWidth, int desiredLength) throws MaterialLengthVariationNotFoundException {
        FrameMaterialCalculator frameCalculator = new BareFrameMaterialCalculator(carport.getFrame(), desiredWidth, desiredLength);
        for (MaterialLengthRecord partRecord : frameCalculator.getAllMaterialRecords()) {
            addPriceOfPartRecordAndExtractAssemblyMaterials(partRecord);
            assemblyMaterialRecords.addPartRecord(partRecord);
        }
        addPriceForRoofTiles(carport, desiredWidth, desiredLength);
        addPriceForAssemblyMaterials();
        float profitMultiplier = carport.getProfitFromMaterials() / 100f + 1;
        return Math.round(price * profitMultiplier);
    }

    private void addPriceForAssemblyMaterials() {
        for (Map.Entry<AssemblyMaterial, Integer> entry : assemblyMaterialRecords.getAssemblyMaterials().entrySet()) {
            AssemblyMaterial assemblyMaterial = entry.getKey();
            Integer amount = entry.getValue();
            price += assemblyMaterial.getPrice() * amount;
        }
    }

    private void addPriceOfPartRecordAndExtractAssemblyMaterials(MaterialLengthRecord materialLengthRecord) {
        price += materialLengthRecord.getCount() * materialLengthRecord.getPrice();
    }

    private void addPriceForRoofTiles(Carport carport, int desiredWidth, int desiredLength) {
        RoofTileCalculator roofTileCalculator = new FlatRoofTileCalculator();
        int roofTiles = roofTileCalculator.getNumberOfTiles(carport.getRoofTile(), desiredWidth, desiredLength);
        price += carport.getRoofTile().getPrice() * roofTiles;
    }
}
