package org.cba.model.cart;

import org.cba.domain.Carport;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.PriceCalculator;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;

import static org.easymock.EasyMock.*;

/**
 * Created by adam on 24/05/2017.
 */
public class SessionCartTest {
    private final Carport carport = Carport.find.byId(1);
    HttpSession session;
    PriceCalculator priceCalculator;

    @BeforeMethod
    public void setUp() throws Exception {
        priceCalculator = new PriceCalculator();
        session = createNiceMock(HttpSession.class);
    }

    @Test
    public void testAddItem() {
        try {
            SessionCart cart = mockEmptyCart();
            Carport carport = Carport.find.byId(1);
            cart.addItem(carport, carport); // Passing carport as settings means default values
            Assert.assertEquals(cart.getNumberOfItems(), 1);
            Assert.assertEquals(cart.getPrice(), priceCalculator.getPrice(carport, carport));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private SessionCart mockEmptyCart() {
        expect(session.getAttribute("cart")).andReturn(null);
        replay(session);
        return new SessionCart(session);
    }

    @Test()
    public void testRemoveItem() throws Exception {
        Carport carport = Carport.find.byId(1);
        int price = priceCalculator.getPrice(carport, carport);
        Purchase mockedCartContents = mockPurchase(carport, price);
        expect(session.getAttribute("cart")).andReturn(mockedCartContents).times(2);
        replay(session);
        SessionCart cart = new SessionCart(session);
        Assert.assertEquals(cart.getNumberOfItems(), 1);
        Assert.assertEquals(cart.getPrice(), price);
        cart.removeItem(0);
        Assert.assertEquals(cart.getNumberOfItems(), 0);
        Assert.assertEquals(cart.getPrice(), 0);
    }

    @Test(expectedExceptions = {IndexOfOrderNotFound.class})
    public void testRemoveNonExistentItem() throws Exception {
        SessionCart cart = mockEmptyCart();
        cart.removeItem(0);
    }

    @NotNull
    private Purchase mockPurchase(Carport carport, int price) {
        Purchase purchase = new Purchase();
        purchase.addPurchaseCarport(new PurchaseCarport(carport, carport, price, purchase));
        purchase.setFinalPrice(price);
        return purchase;
    }

    @Test
    public void testRecalculatePriceForItem() throws Exception {
        int price = priceCalculator.getPrice(carport, carport);
        Purchase mockedPurchase = mockPurchase(carport, price);
        expect(session.getAttribute("cart")).andReturn(mockedPurchase).times(2);
        replay(session);
        SessionCart cart = new SessionCart(session);
        PurchaseCarport mockedOrder = cart.getCartContents().getPurchaseCarports().get(0);
        mockedOrder.setFrameLength(400);
        carport.setFrameLength(400); // Tests with default settings values, except for frame length
        cart.editItem(0, carport);
        Assert.assertEquals(cart.getPrice(), priceCalculator.getPrice(carport, carport));
    }

    @Test
    public void testSaveInDatabaseAndEmptyCart() throws Exception {
        int price = priceCalculator.getPrice(carport, carport);
        Purchase mockedPurchase = mockPurchase(carport, price);
        expect(session.getAttribute("cart")).andReturn(mockedPurchase).times(2);
        replay(session);
        SessionCart cart = new SessionCart(session);
        cart.saveInDatabaseAndEmptyCart();
        Assert.assertTrue(mockedPurchase.getId() != 0);
        Assert.assertEquals(mockedPurchase.getOrderedOn().toString(), new Date(Calendar.getInstance().getTimeInMillis()).toString());
        for (PurchaseCarport purchaseCarport : mockedPurchase.getPurchaseCarports()) {
            Assert.assertTrue(purchaseCarport.getId() != 0);
        }
    }


}