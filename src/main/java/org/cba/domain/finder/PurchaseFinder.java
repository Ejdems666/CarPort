package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.Purchase;
import org.cba.domain.query.QPurchase;

public class PurchaseFinder extends Finder<Integer,Purchase> {

  /**
   * Construct using the default EbeanServer.
   */
  public PurchaseFinder() {
    super(Purchase.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public PurchaseFinder(String serverName) {
    super(Purchase.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QPurchase where() {
     return new QPurchase(db());
  }

  /**
   * Start a new document store query.
   */
  public QPurchase text() {
     return new QPurchase(db()).text();
  }
}
