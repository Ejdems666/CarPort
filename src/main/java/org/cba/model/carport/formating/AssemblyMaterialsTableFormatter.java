package org.cba.model.carport.formating;

import org.cba.components.table.Row;
import org.cba.components.table.TableBuilder;
import org.cba.domain.AssemblyMaterial;

import java.util.Map;

/**
 * Created by adam on 20/05/2017.
 */
public class AssemblyMaterialsTableFormatter implements PartsFormatter {
    private TableBuilder tableBuilder;
    private AssemblyMaterialRecords assemblyMaterialRecords = new AssemblyMaterialRecords();
    private String tableName;

    public AssemblyMaterialsTableFormatter(String tableName) {
        this.tableName = tableName;
        tableBuilder = new TableBuilder();
    }

    public AssemblyMaterialsTableFormatter(String tableName, TableBuilder tableBuilder) {
        this.tableBuilder = tableBuilder;
        this.tableName = tableName;
    }

    @Override
    public void addPartRecord(PartRecord partRecord) {
        assemblyMaterialRecords.addPartRecord(partRecord);
    }

    @Override
    public String getFormattedRecords() {
        tableBuilder.addHeader(tableName, "Name,Amount,Unit,Description");
        addRows();
        return tableBuilder.toString();
    }

    private void addRows() {
        for (Map.Entry<AssemblyMaterial, Integer> entry : assemblyMaterialRecords.getAssemblyMaterials().entrySet()) {
            AssemblyMaterial assemblyMaterial = entry.getKey();
            String amount = String.valueOf(entry.getValue());
            addRow(assemblyMaterial, amount);
        }
    }

    private void addRow(AssemblyMaterial assemblyMaterial, String amount) {
        Row row = tableBuilder.createNewRow();
        row.addColumn(assemblyMaterial.getName());
        row.addColumn(amount);
        row.addColumn("Stk.");
        row.addColumn(assemblyMaterial.getDescription());
    }
}
