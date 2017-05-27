package org.cba.domain.finder;

import io.ebean.Finder;
import org.cba.domain.PurchaseCarport;
import org.cba.domain.query.QPurchaseCarport;

public class PurchaseCaportFinder extends Finder<Integer,PurchaseCarport> {

  /**
   * Construct using the default EbeanServer.
   */
  public PurchaseCaportFinder() {
    super(PurchaseCarport.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public PurchaseCaportFinder(String serverName) {
    super(PurchaseCarport.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  public QPurchaseCarport where() {
     return new QPurchaseCarport(db());
  }

  /**
   * Start a new document store query.
   */
  public QPurchaseCarport text() {
     return new QPurchaseCarport(db()).text();
  }
}
