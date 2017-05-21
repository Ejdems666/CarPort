package org.cba.model.carport.formating;

import org.cba.domain.AssemblyMaterial;
import org.cba.domain.MaterialDependency;
import org.cba.domain.MaterialLength;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by adam on 20/05/2017.
 */
public class AssemblyMaterialRecordsTest {

    private AssemblyMaterialRecords assemblyMaterialRecords;

    @BeforeMethod
    public void setUp() throws Exception {
        assemblyMaterialRecords = new AssemblyMaterialRecords();
    }

    @Test
    public void testAddSinglePartRecord() throws Exception {
        MaterialLength materialLV = MaterialLength.find.byId(3);
        PartRecord partRecord = new MaterialLengthRecord(materialLV, 1);

        assemblyMaterialRecords.addPartRecord(partRecord);
        Map<AssemblyMaterial, Integer> assemblyMaterialsMap = assemblyMaterialRecords.getAssemblyMaterials();

        MaterialDependency materialDependency = materialLV.getMaterial().getMaterialDependencies().get(0);
        Assert.assertEquals(assemblyMaterialsMap.get(materialDependency.getAssemblyMaterial()), Integer.valueOf(5));
    }

    @Test
    public void testAddTwoSamePartRecords() throws Exception {
        MaterialLength materialLV = MaterialLength.find.byId(3);

        assemblyMaterialRecords.addPartRecord(new MaterialLengthRecord(materialLV, 1));
        assemblyMaterialRecords.addPartRecord(new MaterialLengthRecord(MaterialLength.find.byId(1), 2));
        Map<AssemblyMaterial, Integer> assemblyMaterialsMap = assemblyMaterialRecords.getAssemblyMaterials();

        MaterialDependency materialDependency = materialLV.getMaterial().getMaterialDependencies().get(0);
        Assert.assertEquals(assemblyMaterialsMap.get(materialDependency.getAssemblyMaterial()), Integer.valueOf(9));
    }

    @Test
    public void testAddTwoDifferentPartRecords() throws Exception {
        MaterialLength materialLV = MaterialLength.find.byId(3);
        MaterialLength materialLV2 = MaterialLength.find.byId(2);

        assemblyMaterialRecords.addPartRecord(new MaterialLengthRecord(materialLV, 1));
        assemblyMaterialRecords.addPartRecord(new MaterialLengthRecord(materialLV2, 1));
        Map<AssemblyMaterial, Integer> assemblyMaterialsMap = assemblyMaterialRecords.getAssemblyMaterials();

        MaterialDependency materialDependency = materialLV.getMaterial().getMaterialDependencies().get(0);
        Assert.assertEquals(assemblyMaterialsMap.get(materialDependency.getAssemblyMaterial()), Integer.valueOf(5));

        materialDependency = materialLV2.getMaterial().getMaterialDependencies().get(0);
        Assert.assertEquals(assemblyMaterialsMap.get(materialDependency.getAssemblyMaterial()), Integer.valueOf(10));
    }
}