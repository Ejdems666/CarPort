package org.cba.model.carport.calculation;

import org.cba.domain.AssemblyMaterial;
import org.cba.domain.Carport;
import org.cba.model.carport.calculation.exception.MaterialLengthVariationNotFoundException;
import org.cba.model.carport.formating.AssemblyMaterialRecords;
import org.cba.model.carport.formating.PartRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by adam on 20/04/2017.
 */
public class PriceCalculator {

    private final MaterialCalculatorFactory factory = new MaterialCalculatorFactory();
    private int price;
    private AssemblyMaterialRecords assemblyMaterialRecords;

    public int getPrice(Carport carport, CarportSettings settings) throws MaterialLengthVariationNotFoundException {
        price = 0;
        assemblyMaterialRecords = new AssemblyMaterialRecords();
        List<MaterialCalculator> calculators = factory.getMaterialCalculators(carport, settings);
        for (MaterialCalculator calculator : calculators) {
            for (PartRecord partRecord : calculator.getAllPartRecords()) {
                addPriceOfPartRecord(partRecord);
                assemblyMaterialRecords.addPartRecord(partRecord);
            }
        }
        addPriceForAssemblyMaterials();
        float profitMultiplier = carport.getProfitFromMaterials() / 100f + 1;
        return Math.round(price * profitMultiplier);
    }

    private void addPriceOfPartRecord(PartRecord partRecord) {
        price += partRecord.getCount() * partRecord.getPrice();
    }

    private void addPriceForAssemblyMaterials() {
        for (Map.Entry<AssemblyMaterial, Integer> entry : assemblyMaterialRecords.getAssemblyMaterials().entrySet()) {
            AssemblyMaterial assemblyMaterial = entry.getKey();
            Integer amount = entry.getValue();
            price += assemblyMaterial.getPrice() * amount;
        }
    }
}
