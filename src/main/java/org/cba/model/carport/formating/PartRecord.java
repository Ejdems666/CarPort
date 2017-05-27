package org.cba.model.carport.formating;

import org.cba.domain.PartDependency;

import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public interface PartRecord{
    String getName();
    String getDescription();
    Integer getLength();
    Integer getWidth();
    Integer getHeight();
    Integer getPrice();

    public List<PartDependency> getPartDependencies();

    public int getCount();
}