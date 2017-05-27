package org.cba.model.carport.formating;

import org.cba.domain.PartDependency;
import org.cba.domain.RoofTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 16/05/2017.
 */
public class RoofTileRecord implements PartRecord {
    private RoofTile roofTile;
    private int amount;

    public RoofTileRecord(RoofTile roofTile, int amount) {
        this.roofTile = roofTile;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return roofTile.getName();
    }

    @Override
    public String getDescription() {
        return roofTile.getDescription();
    }

    @Override
    public Integer getLength() {
        return roofTile.getLength();
    }

    @Override
    public Integer getWidth() {
        return roofTile.getWidth();
    }

    @Override
    public Integer getHeight() {
        return 0;
    }

    @Override
    public Integer getPrice() {
        return roofTile.getPrice();
    }

    @Override
    public List<PartDependency> getPartDependencies() {
        return new ArrayList<>(roofTile.getRoofTileDependencies());
    }

    public int getCount() {
        return amount;
    }
}
