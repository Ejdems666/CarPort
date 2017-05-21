package org.cba.model.carport.formating;

import org.cba.domain.MaterialLength;
import org.cba.domain.PartDependency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 09/05/2017.
 */
public class MaterialLengthRecord implements PartRecord {
    private MaterialLength materialLengthVariation;
    private int count;

    public MaterialLengthRecord(MaterialLength materialLengthVariation, int count) {
        this.materialLengthVariation = materialLengthVariation;
        this.count = count;
    }

    @Override
    public String getName() {
        return materialLengthVariation.getMaterial().getName();
    }

    @Override
    public String getDescription() {
        return materialLengthVariation.getMaterial().getDescription();
    }

    @Override
    public Integer getLength() {
        return materialLengthVariation.getLength();
    }

    @Override
    public Integer getWidth() {
        return materialLengthVariation.getMaterial().getWidth();
    }

    @Override
    public Integer getHeight() {
        return materialLengthVariation.getMaterial().getHeight();
    }

    public MaterialLength getPart() {
        return materialLengthVariation;
    }

    @Override
    public List<PartDependency> getPartDependencies() {
        // TODO: find better solution
        return new ArrayList<>(materialLengthVariation.getMaterial().getMaterialDependencies());
    }

    public int getCount() {
        return count;
    }
}
