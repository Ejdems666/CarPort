package org.cba.domain;

import org.cba.domain.finder.PurchaseFinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 22/05/2017.
 */
@Entity
public class Purchase {
    public static final PurchaseFinder find = new PurchaseFinder();

    @Id
    private int id;

    /**
     * Every Purchase will have saved price, because calculation might change in the future, or % of material profit
     */
    @NotNull
    private int finalPrice;

    private Date orderedOn;

    @ManyToOne
    private User customer;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseCarport> purchaseCarports = new ArrayList<>();

    public List<PurchaseCarport> getPurchaseCarports() {
        return purchaseCarports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(Date orderedOn) {
        this.orderedOn = orderedOn;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void addPurchaseCarport(PurchaseCarport purchaseCarport) {
        purchaseCarports.add(purchaseCarport);
    }
}
