package org.cba.model.carport.formating;

/**
 * Created by adam on 16/05/2017.
 */
public interface PartRecordsFormatter {
    public void addPartRecord(PartRecord partRecord);

    public String getFormattedRecords(String tableName);

    public String printAssemblyMaterialTable(String tableName);
}
