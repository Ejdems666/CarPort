package org.cba.model.carport.formating;

import org.cba.domain.MaterialDependency;

import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public interface PartRecord{
    String getName();
    String getDescription();
    Integer getLength();

    public List<MaterialDependency> getPartDependencies();

    public int getCount();
}