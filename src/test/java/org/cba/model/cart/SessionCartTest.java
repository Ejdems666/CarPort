package org.cba.model.cart;

import org.cba.domain.Carport;
import org.cba.domain.Purchase;
import org.cba.domain.PurchaseCarport;
import org.cba.model.carport.calculation.Dimensions;
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
    HttpSession session;

    PriceCalculator priceCalculator;
    private final Carport carport = Carport.find.byId(1);
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
            cart.addItem(carport, carport.getDefaultDimensions());
            Assert.assertEquals(cart.getNumberOfItems(), 1);
            Assert.assertEquals(cart.getPrice(), priceCalculator.getPrice(carport, carport.getDefaultDimensions()));
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
        int price = priceCalculator.getPrice(carport, carport.getDefaultDimensions());
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
        purchase.addPurchaseCarport(new PurchaseCarport(carport, carport.getDefaultDimensions(), price, purchase));
        purchase.setFinalPrice(price);
        return purchase;
    }

    @Test
    public void testRecalculatePriceForItem() throws Exception {
        int price = priceCalculator.getPrice(carport, carport.getDefaultDimensions());
        Purchase mockedPurchase = mockPurchase(carport, price);
        expect(session.getAttribute("cart")).andReturn(mockedPurchase).times(2);
        replay(session);
        SessionCart cart = new SessionCart(session);
        PurchaseCarport mockedOrder = cart.getCartContents().getPurchaseCarports().get(0);
        mockedOrder.setFrameLength(400);
        cart.recalculatePriceForItem(0);
        Assert.assertEquals(cart.getPrice(),priceCalculator.getPrice(carport, new Dimensions(400,carport.getDefaultWidth())));
    }

    @Test
    public void testSaveInDatabaseAndEmptyCart() throws Exception {
        int price = priceCalculator.getPrice(carport, carport.getDefaultDimensions());
        Purchase mockedPurchase = mockPurchase(carport, price);
        expect(session.getAttribute("cart")).andReturn(mockedPurchase).times(2);
        replay(session);
        SessionCart cart = new SessionCart(session);
        cart.saveInDatabaseAndEmptyCart();
        Assert.assertTrue(mockedPurchase.getId() != 0);
        Assert.assertEquals(mockedPurchase.getOrderedOn().toString(),new Date(Calendar.getInstance().getTimeInMillis()).toString());
        for (PurchaseCarport purchaseCarport : mockedPurchase.getPurchaseCarports()) {
            Assert.assertTrue(purchaseCarport.getId() != 0);
        }
    }


}