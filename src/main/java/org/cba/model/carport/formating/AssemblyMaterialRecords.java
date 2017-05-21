package org.cba.model.carport.formating;

import org.cba.domain.AssemblyMaterial;
import org.cba.domain.PartDependency;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 20/05/2017.
 * Accumulates PartRecords and stores and merges all of it's dependent assembly materials into single HashMap
 */
public class AssemblyMaterialRecords {
    private HashMap<AssemblyMaterial, Integer> assemblyMaterials = new HashMap<>();

    public void addPartRecord(PartRecord partRecord) {
        for (PartDependency partDependency : partRecord.getPartDependencies()) {
            AssemblyMaterial assemblyMaterial = partDependency.getAssemblyMaterial();
            int amount = partDependency.getAmountPerUnit() * partRecord.getCount();
            if (assemblyMaterials.containsKey(assemblyMaterial)) {
                int oldAmount = assemblyMaterials.get(assemblyMaterial);
                assemblyMaterials.replace(assemblyMaterial, oldAmount + amount);
            } else {
                assemblyMaterials.put(assemblyMaterial, amount);
            }
        }
    }

    public Map<AssemblyMaterial, Integer> getAssemblyMaterials() {
        return assemblyMaterials;
    }
}
