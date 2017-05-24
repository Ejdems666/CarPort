package org.cba.domain;

import org.cba.model.carport.calculation.Dimensions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by adam on 22/05/2017.
 */
@Entity
public class PurchaseCarport {
    public PurchaseCarport(Carport carport, Dimensions frameDimensions, int price) {
        this.carport = carport;
        frameWidth = frameDimensions.width;
        frameLength = frameDimensions.length;
        this.price = price;
    }

    @Id
    private int id;

    @ManyToOne
    @NotNull
    private Carport carport;

    @NotNull
    private int frameWidth;
    @NotNull
    private int frameLength;

    /**
     * Every Purchase will have saved price, because calculation might change in the future, or % of material profit
     */
    @NotNull
    private int price;

    @NotNull
    @ManyToOne
    private Purchase purchase;

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

    public Dimensions getFrameDimensions() {
        return new Dimensions(frameLength,frameWidth);
    }

    public void setFrameDimensions(Dimensions dimensions) {
        frameLength = dimensions.length;
        frameWidth = dimensions.width;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
