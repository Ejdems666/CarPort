package org.cba.domain;

import org.cba.domain.finder.PurchaseCarportFinder;
import org.cba.model.carport.calculation.CarportSettings;
import org.cba.model.carport.calculation.Dimensions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 22/05/2017.
 */
@Entity
public class PurchaseCarport implements CarportSettings {
    public static final PurchaseCarportFinder find = new PurchaseCarportFinder();

    @Id
    private int id;

    @ManyToOne
    @NotNull
    private Carport carport;

    @NotNull
    private int frameWidth;

    @NotNull
    private int frameLength;

    private boolean withShed;
    @NotNull
    private int shedWidth;
    @NotNull
    private int shedLength;

    private String pdfCatalogue;

    /**
     * Every Purchase will have saved price, because calculation might change in the future, or % of material profit
     */
    @NotNull
    private int price;

    @NotNull
    @ManyToOne
    private Purchase purchase;

    public PurchaseCarport(Carport carport, CarportSettings settings, int price, Purchase purchase) {
        this.carport = carport;
        setFrameDimensions(settings.getFrameDimensions());
        setShedDimensions(settings.getShedDimensions());
        this.withShed = settings.isWithShed();
        this.price = price;
        this.purchase = purchase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameLength() {
        return frameLength;
    }

    public void setFrameLength(int frameLength) {
        this.frameLength = frameLength;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPdfCatalogue() {
        return pdfCatalogue;
    }

    public void setPdfCatalogue(String pdfCatalogue) {
        this.pdfCatalogue = pdfCatalogue;
    }

    @Override
    public Dimensions getFrameDimensions() {
        return new Dimensions(frameLength, frameWidth);
    }

    public void setFrameDimensions(Dimensions dimensions) {
        frameLength = dimensions.length;
        frameWidth = dimensions.width;
    }

    @Override
    public boolean isWithShed() {
        return withShed;
    }

    public void setWithShed(boolean withShed) {
        this.withShed = withShed;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    @Override
    public Dimensions getShedDimensions() {
        return new Dimensions(shedLength, shedWidth);
    }

    public void setShedDimensions(Dimensions dimensions) {
        shedLength = dimensions.length;
        shedWidth = dimensions.width;
    }
}
